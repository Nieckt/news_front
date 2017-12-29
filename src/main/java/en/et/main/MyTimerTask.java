package en.et.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import en.et.moder.MyNews;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class MyTimerTask extends TimerTask {
	MyNews my = new MyNews();
	
	//实现类
	@Override
	public void run() {
		try {
			// 生成html
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			// 配置ftl查找目录
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
			// 设置数据的抓取模式
			cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
			List<Map> result = my.queryNews();
			Map root = new HashMap();
			root.put("newsList", result);
			// 数据与模板关联
			// 实例化模板对象
			Template temp = cfg.getTemplate("index.ftl");

			String saveFile = "src/main/webapp/index.html";
			// 生成html 输出到目标
			Writer out = new OutputStreamWriter(new FileOutputStream(saveFile));
			temp.process(root, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

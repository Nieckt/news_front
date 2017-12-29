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
	
	//ʵ����
	@Override
	public void run() {
		try {
			// ����html
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			// ����ftl����Ŀ¼
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
			// �������ݵ�ץȡģʽ
			cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
			List<Map> result = my.queryNews();
			Map root = new HashMap();
			root.put("newsList", result);
			// ������ģ�����
			// ʵ����ģ�����
			Template temp = cfg.getTemplate("index.ftl");

			String saveFile = "src/main/webapp/index.html";
			// ����html �����Ŀ��
			Writer out = new OutputStreamWriter(new FileOutputStream(saveFile));
			temp.process(root, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

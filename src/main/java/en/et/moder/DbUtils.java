package en.et.moder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DbUtils {
	//jdbj的关键
	/*public static Connection getConnection(){
		try {
			//第一步加载drivet类
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取url地址     jdbc:mysql://[ip]:[端口]/数据库名
		String url = "jdbc:mysql://localhost:3306/easytop";
		
		//获取连接数据库的连接      Connection
		Connection conn = null ;
		try {
			conn = DriverManager.getConnection(url, "root", "123456");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}*/
	/**
	 * 封装类
	 */
	static Properties p = new Properties();
	static{
		//输出流
		//getResourceAsStream查找具有给定名称的资源
		InputStream is = DbUtils.class.getResourceAsStream("/jdbc.properties");
		try {
			//从输入流中读取属性列表（键和元素对）
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 * 	通过路径url
	 * 	驱动程序
	 * 	数据库账号
	 * 	数据库密码
	 */
	public static Connection getConnection()throws Exception {
		String url = p.getProperty("url");
		String driverClass = p.getProperty("driverClass");
		String name = p.getProperty("username");
		String password = p.getProperty("password");
		
		Class.forName(driverClass);
		//获取数据的链接
		Connection conn = DriverManager.getConnection(url, name, password);
		return conn;
	}
	
	//查询数据
	public static List<Map> query(String sql)throws Exception {
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		List list = new ArrayList();
		//获取列的个数
		int columnCount = rsmd.getColumnCount();
		while(rs.next()) {
			Map map = new HashMap();
			//sql中必须要等于1
			for (int i = 1; i <= columnCount; i++) {
				String colName = rsmd.getColumnName(i);
				String colValue = rs.getString(i);
				map.put(colName, colValue);
			}
			list.add(map);
		}
		conn.close();
		pst.cancel();
		rs.close();
		return list;
	}
	/*public static void main(String[] args) throws Exception {
		List<Map> result = query("select * from eclass");
		System.out.println(result);
	}*/
	//传入sql语句
	public static int execute(String sql) throws Exception {
		//连接数据库
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		int i = pst.executeUpdate();
		conn.close();
		pst.cancel();
		return i;
	}
}

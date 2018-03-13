package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * 插入100条数据
 * @author Administrator
 *
 */
public class Demo3 {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//使用try-with-resource的方式自动关闭连接
		//因为Connection和Statement都实现了AutoCloseable接口
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				Statement s = c.createStatement();
		)
		{
			for (int i = 0; i < 100; i++) {
				String sql = "insert into hero values(null," + "'hero" + i+ "'" + "," + 314.0f + "," + 50 + ")";
				s.execute(sql);
			}
		System.out.println("成功插入100条数据");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * 修改
 * @author Administrator
 *
 */
public class Demo06 {
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
				//修改的SQL
				String sql = "update hero set name = 'name 5' where id = 3";
				s.execute(sql);			
				System.out.println("修改一条数据");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

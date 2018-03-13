package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Demo02 {
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
			String sql = "insert into hero values(null," + "'德玛'" + "," + 314.0f + "," + 50 + ")";
			s.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

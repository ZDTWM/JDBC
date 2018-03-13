package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 验证用户和密码
 * @author Administrator
 *
 */
public class Demo08 {
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
			String name = "dashen";
			//正确的密码是：thisispassword
			String password = "thisispassword";
			String sql = "select * from user where name = '" + name +"' and password = '" + password+"'";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
				System.out.println("账号密码正确");
			else
				System.out.println("账号密码错误");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

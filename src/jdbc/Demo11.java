package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 预编译Statement
 * @author Administrator
 *
 */
public class Demo11 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//使用try-with-resource的方式自动关闭连接
		//因为Connection和Statement都实现了AutoCloseable接口
		String sql = "insert into hero values(null,?,?,?)";
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				// 根据sql语句创建PreparedStatement
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{	
			//设置参数
			ps.setString(1, "提莫");
			ps.setFloat(2, 313.0f);
			ps.setInt(3, 50);
			//执行
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
}

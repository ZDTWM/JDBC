package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查询数据
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
				//查询的SQL
				String sql = "select * from hero";
				// 执行查询语句，并把结果集返回给ResultSet
				ResultSet rs = s.executeQuery(sql);		
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString(2);
					float hp = rs.getFloat("hp");
					int damage = rs.getInt(4);
					System.out.printf("%d\t%s\t%f\t%d%n",id,name,hp,damage);
				}
		         // 不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
	            // rs.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

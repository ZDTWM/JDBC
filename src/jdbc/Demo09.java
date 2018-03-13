package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * 获取总数
 * @author Administrator
 *
 */
public class Demo09 {
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
			String sql = "select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			int total = 0;
			while(rs.next()){
				total = rs.getInt(1);
			}
			System.out.println("表中共有：" + total + " 条数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

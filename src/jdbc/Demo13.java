package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * execute与executeUpdate的区别
 * @author Administrator
 *
 */
public class Demo13 {
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
				Statement s = c.createStatement();
		)
		{	
            // 不同1：execute可以执行查询语句
            // 然后通过getResultSet，把结果集取出来
			String sqlSelect = "select * from hero";
			s.execute(sqlSelect);
			ResultSet rs = s.getResultSet();
			while(rs.next()){
				System.out.println(rs.getInt("id"));
			}
			// executeUpdate不能执行查询语句
			//s.executeUpdate(sqlSelect);
			
            // 不同2:
            // execute返回boolean类型，true表示执行的是查询语句，false表示执行的是insert,delete,update等等
			boolean isSelect  = s.execute(sqlSelect);
			System.out.println(isSelect);//true
			
			// executeUpdate返回的是int，表示有多少条数据受到了影响
			String sqlUpdate = "update Hero set hp = 300 where id < 100";
			int number = s.executeUpdate(sqlUpdate);
			System.out.println(number);//98
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
}

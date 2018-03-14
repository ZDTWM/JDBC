package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *	事务：使用事务
 */
public class Demo17 {
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
            // 有事务的前提下
            // 在事务中的多个操作，要么都成功，要么都失败
			c.setAutoCommit(false);
			
			//加血的SQL
			String sql1 = "update hero set hp = hp + 1 where id = 2";
			s.execute(sql1);
			
			//减血的SQL
			//不小心写错写成了 updata(而非update)
			String sql2 = "updata hero set hp = hp -1 where id = 2";
			s.execute(sql2);
			//手动提交
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

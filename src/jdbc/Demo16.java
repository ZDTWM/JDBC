package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * 使用try-with-resource的方式自动关闭连接
 * @author Administrator
 *
 */
public class Demo16 {
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
            //没有事务的前提下
            //假设业务操作时，加血，减血各做一次
            //结束后，英雄的血量不变
			
			//加血的SQL
			String sql1 = "update hero set hp = hp +1 where id = 1";
			s.execute(sql1);
			
			//减血的SQL
			//不小心写错写成了 updata(而非update)
			String sql2 = "updata hero set hp = hp -1 where id = 22";
			s.execute(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

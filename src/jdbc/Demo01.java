package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		/**
		 * 1、初始化驱动
		 */
		try {
            //驱动类com.mysql.jdbc.Driver
            //就在 mysql-connector-java-5.0.8-bin.jar中
            //如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
			Class.forName("com.mysql.jdbc.Driver");
			/**
			 * 2、建立与数据库的连接
			 */
            // 建立与数据库的Connection连接
            // 这里需要提供：
            // 数据库所处于的ip:127.0.0.1 (本机)
            // 数据库的端口号： 3306 （mysql专用端口号）
            // 数据库名称 how2java
            // 编码方式 UTF-8
            // 账号 root
            // 密码 admin
			 c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
                    "root", "admin");
			System.out.println("连接成功，获取连接对象： " + c);
			/**
			 * 3、创建Statement
			 * 	Statement是用于执行SQL语句的，比如增加，删除
			 */
			 s = c.createStatement();
			System.out.println("获取 Statement对象： " + s);
			/**
			 * 4、执行SQL语句
			 */
			String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";
			s.execute(sql);
			System.out.println("执行插入语句成功");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			/**
			 * 5、关闭连接
			 * 	数据库的连接时有限资源，相关操作结束后，养成关闭数据库的好习惯
			 *  (1)先关闭Statement
			 *  (2)后关闭Connection
			 */
			if(s != null)
				try {
					//先关闭Statement
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(c != null)
				try {
					//后关闭Connection
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}

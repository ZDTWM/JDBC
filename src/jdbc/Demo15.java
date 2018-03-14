package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 使用try-with-resource的方式自动关闭连接
 * @author Administrator
 *
 */
public class Demo15 {
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
		)
		{
			//查看数据库层面的元数据
			//即数据库服务器版本，驱动版本，都有哪些数据库等等
			DatabaseMetaData dbmd = c.getMetaData();
			
			// 获取数据库服务器产品名称
			System.out.println("数据库产品名称：" + dbmd.getDatabaseProductName());
			// 获取数据库服务器产品版本号
			System.out.println("数据库产品版本：" + dbmd.getDatabaseProductVersion());
			// 获取数据库服务器用作类别和表名之间的分隔符 如test.user
			System.out.println("数据库和表分隔符：" + dbmd.getCatalogSeparator());
			//驱动名称
			System.out.println("驱动名称：" + dbmd.getDriverName());
			// 获取驱动版本
			System.out.println("驱动版本：" + dbmd.getDriverVersion());
			
			System.out.println("可用数据库列表：");
			ResultSet rs = dbmd.getCatalogs();
			while(rs.next()){
				System.out.println("数据库名称：" + rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

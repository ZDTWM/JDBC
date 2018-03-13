package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * 分页查询
 * @author Administrator
 *
 */
public class Demo10 {
	public static void main(String[] args) {
		//start 表示开始页数，count表示一页显示的总数
		//list(0,5) 表示第一页，一共显示5条数据
		//list(10,5) 表示第三页，一共显示5条数据
		list(10,5);
	}
	
	public static void list(int start,int count){
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
			//分页查询SQL
			String sql = "select * from hero limit " + start + "," + count;
			// 执行查询语句，并把结果集返回给ResultSet
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

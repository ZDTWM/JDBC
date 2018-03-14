package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 	ORM :Object Relationship Database Mapping 
 * 	对象和关系数据库的映射 
 *	简单说，一个对象，对应数据库里的一条记录
 */
public class Demo18 {
	public static void main(String[] args) {
		Hero h = get(2);
		System.out.println(h.name);
		
	}
	
	public static Hero get(int id){
		Hero hero = null;
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
			String sql = "select * from hero where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			
	        // 因为id是唯一的，ResultSet最多只能有一条记录
            // 所以使用if代替while
			if(rs.next()){
				hero = new Hero();
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				hero.id = id;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hero;
	}
	
}

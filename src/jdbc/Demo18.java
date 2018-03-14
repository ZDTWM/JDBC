package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * 	ORM :Object Relationship Database Mapping 
 * 	对象和关系数据库的映射 
 *	简单说，一个对象，对应数据库里的一条记录
 */
public class Demo18 {
	public static void main(String[] args) {
		/**
		 * 根据id获取对象
		 */
		Hero h1 = get(2);
		System.out.println(h1.name);
		
		List<Hero> hs = list();
		System.out.println("数据库中总共有：" + hs.size() + "条数据");
		
		Hero h = new Hero();
		h.name = "新的英雄";
		System.out.println("新加一条数据：");
		add(h);
		hs = list();
		System.out.println("数据库中总共有：" + hs.size() + "条数据");
		
		System.out.println("取出id=101的数据，它的name是:");
		h = get(101);
		System.out.println(h.name);
		
		System.out.println("把名字改为 英雄101，并且更新到数据库");
		h.name = "英雄101";
		update(h);
		
		System.out.println("取出id=101的数据，它的name是:");
		h = get(101);
		System.out.println(h.name);
		
		System.out.println("删除id=101的数据");
		delete(h);
		
		hs = list();
		System.out.println("数据库中总共有" + hs.size() + " 条数据");
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 往数据库中插入一个对象
	 * @param h
	 */
	public static void add(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "insert into hero values(null,?,?,?)";
		//使用try-with-resource的方式自动关闭连接
		//因为Connection和Statement都实现了AutoCloseable接口
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//设置参数
			ps.setString(1, h.name);
			ps.setFloat(2, h.hp);
			ps.setInt(3, h.damage);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 删除
	 * @param h
	 */
	public static void delete(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "delete from hero where id = ?";
		//使用try-with-resource的方式自动关闭连接
		//因为Connection和Statement都实现了AutoCloseable接口
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//设置参数
			ps.setInt(1, h.id);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 更新
	 * @param h
	 */
	public static void update(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "update hero set name = ? , hp = ? , damage = ? where id = ?";
		//使用try-with-resource的方式自动关闭连接
		//因为Connection和Statement都实现了AutoCloseable接口
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//设置参数
			ps.setString(1, h.name);
			ps.setFloat(2, h.hp);
			ps.setInt(3, h.damage);
			ps.setInt(4, h.id);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 把所有的Hero数据查询出来，转换为Hero对象后，放在一个集合中返回
	 * @return
	 */
	public static List<Hero> list(){
		List<Hero> heros = new ArrayList<>();
		
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
			String sql = "select * from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				Hero hero = new Hero();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				hero.id = id;
				heros.add(hero);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return heros;
	}
	
}

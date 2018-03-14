package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 	ORM :Object Relationship Database Mapping 
 * 	����͹�ϵ���ݿ��ӳ�� 
 *	��˵��һ�����󣬶�Ӧ���ݿ����һ����¼
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
		//ʹ��try-with-resource�ķ�ʽ�Զ��ر�����
		//��ΪConnection��Statement��ʵ����AutoCloseable�ӿ�
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				Statement s = c.createStatement();
		)
		{
			String sql = "select * from hero where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			
	        // ��Ϊid��Ψһ�ģ�ResultSet���ֻ����һ����¼
            // ����ʹ��if����while
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

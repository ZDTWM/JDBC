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
 * 	����͹�ϵ���ݿ��ӳ�� 
 *	��˵��һ�����󣬶�Ӧ���ݿ����һ����¼
 */
public class Demo18 {
	public static void main(String[] args) {
		/**
		 * ����id��ȡ����
		 */
		Hero h1 = get(2);
		System.out.println(h1.name);
		
		List<Hero> hs = list();
		System.out.println("���ݿ����ܹ��У�" + hs.size() + "������");
		
		Hero h = new Hero();
		h.name = "�µ�Ӣ��";
		System.out.println("�¼�һ�����ݣ�");
		add(h);
		hs = list();
		System.out.println("���ݿ����ܹ��У�" + hs.size() + "������");
		
		System.out.println("ȡ��id=101�����ݣ�����name��:");
		h = get(101);
		System.out.println(h.name);
		
		System.out.println("�����ָ�Ϊ Ӣ��101�����Ҹ��µ����ݿ�");
		h.name = "Ӣ��101";
		update(h);
		
		System.out.println("ȡ��id=101�����ݣ�����name��:");
		h = get(101);
		System.out.println(h.name);
		
		System.out.println("ɾ��id=101������");
		delete(h);
		
		hs = list();
		System.out.println("���ݿ����ܹ���" + hs.size() + " ������");
	}
	
	/**
	 * ����id����
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
	
	/**
	 * �����ݿ��в���һ������
	 * @param h
	 */
	public static void add(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "insert into hero values(null,?,?,?)";
		//ʹ��try-with-resource�ķ�ʽ�Զ��ر�����
		//��ΪConnection��Statement��ʵ����AutoCloseable�ӿ�
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//���ò���
			ps.setString(1, h.name);
			ps.setFloat(2, h.hp);
			ps.setInt(3, h.damage);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * ɾ��
	 * @param h
	 */
	public static void delete(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "delete from hero where id = ?";
		//ʹ��try-with-resource�ķ�ʽ�Զ��ر�����
		//��ΪConnection��Statement��ʵ����AutoCloseable�ӿ�
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//���ò���
			ps.setInt(1, h.id);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ����
	 * @param h
	 */
	public static void update(Hero h){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "update hero set name = ? , hp = ? , damage = ? where id = ?";
		//ʹ��try-with-resource�ķ�ʽ�Զ��ر�����
		//��ΪConnection��Statement��ʵ����AutoCloseable�ӿ�
		try(
				Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8",
		                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//���ò���
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
	 * �����е�Hero���ݲ�ѯ������ת��ΪHero����󣬷���һ�������з���
	 * @return
	 */
	public static List<Hero> list(){
		List<Hero> heros = new ArrayList<>();
		
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

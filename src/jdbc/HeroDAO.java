package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO implements DAO {
	/**
	 * 1��
	 * �������ĳ�ʼ�������˹��췽��HeroDAO��
	 * ��Ϊ������ʼ��ֵ��Ҫִ��һ�Σ����Է�����������ʣ�����������Ҳ����Ҫд�ˣ���������
	 */
	public HeroDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 2��
	 * �ṩ��һ��getConnection������������
	 * ���е����ݿ��������Ҫ�����õ�һ�����ݿ�����Connection��
	 * ��ǰ������ÿ�������ﶼ��дһ�������Ҫ�Ķ����룬
	 * ��ôÿ���ط�����Ҫ�޸ġ� ͨ�����ַ�ʽ��
	 * ֻ��Ҫ�޸���һ���ط��Ϳ����ˡ� �����ø�����ά����
	 * ����Ҳ���Ӽ�ࡣ
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/twm?characterEncoding=UTF-8", "root",
                "admin");
		
	}
	
	/**
	 * ��ȡ����
	 * @return
	 */
	public int getTotal(){
		int total = 0;
		try(
				Connection c = getConnection();
				Statement s = c.createStatement();
		) 
		{
			String sql = "select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				total = rs.getInt(1);
			}
			System.out.println("total:" + total);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return total;
	}
	
	/**
	 * ����
	 */
	@Override
	public void add(Hero hero) {
		String sql = "insert into hero values(null,?,?,?)";
		try(
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//���ò���
			ps.setString(1, hero.name);
			ps.setFloat(2, hero.hp);
			ps.setInt(3, hero.damage);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				hero.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ����
	 */
	@Override
	public void update(Hero hero) {
		String sql = "update hero set name = ? , hp = ? , damage = ? , where id = ?";
		try(
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			//���ò���
			ps.setString(1, hero.name);
			ps.setFloat(2, hero.hp);
			ps.setInt(3, hero.damage);
			ps.setInt(4, hero.id);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * ɾ��
	 */
	@Override
	public void delete(int id) {
		
		try(
				Connection c = getConnection();
				Statement s = c.createStatement();
		)
		{
			String sql = "delete from hero where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	/**
	 * ����id��ȡ����
	 */
	@Override
	public Hero get(int id) {
		Hero hero = null;
		try(
				Connection c = getConnection();
				Statement s = c.createStatement();
		)
		{
			String sql = "select * from hero where id = " + id;
			ResultSet rs = s.executeQuery(sql);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hero;
	}
	
	/**
	 * �����е�Hero���ݲ�ѯ������ת��ΪHero����󣬷���һ�������з���
	 */
	@Override
	public List<Hero> list() {
		return list(0,Short.MAX_VALUE);
	}
	/**
	 * �����е�Hero���ݲ�ѯ������ת��ΪHero����󣬷���һ�������з���
	 * ��ҳ��ѯ
	 */
	@Override
	public List<Hero> list(int start, int count) {
		List<Hero> heros = new ArrayList<Hero>();
		String sql = "select * from hero order by id desc limit ?,? ";
		try(
				Connection c = getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
		)
		{
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Hero hero = new Hero();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				hero.id = id;
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				heros.add(hero);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heros;
	}

}
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * ��ҳ��ѯ
 * @author Administrator
 *
 */
public class Demo10 {
	public static void main(String[] args) {
		//start ��ʾ��ʼҳ����count��ʾһҳ��ʾ������
		//list(0,5) ��ʾ��һҳ��һ����ʾ5������
		//list(10,5) ��ʾ����ҳ��һ����ʾ5������
		list(10,5);
	}
	
	public static void list(int start,int count){
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
			//��ҳ��ѯSQL
			String sql = "select * from hero limit " + start + "," + count;
			// ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
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

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * ����
 * @author Administrator
 *
 */
public class Demo04 {
	public static void main(String[] args) {
		
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
				//���ӵ�SQL
				String sql = "insert into hero values(null," + "'addHero'" + "," + 500.0f + "," + 40 + ")";
				s.execute(sql);			
		System.out.println("����һ������");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

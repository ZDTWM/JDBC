package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * ����100������
 * @author Administrator
 *
 */
public class Demo3 {
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
			for (int i = 0; i < 100; i++) {
				String sql = "insert into hero values(null," + "'hero" + i+ "'" + "," + 314.0f + "," + 50 + ")";
				s.execute(sql);
			}
		System.out.println("�ɹ�����100������");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

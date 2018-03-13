package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * ��ȡ����
 * @author Administrator
 *
 */
public class Demo09 {
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
			String sql = "select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			int total = 0;
			while(rs.next()){
				total = rs.getInt(1);
			}
			System.out.println("���й��У�" + total + " ������");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

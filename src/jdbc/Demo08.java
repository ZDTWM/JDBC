package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��֤�û�������
 * @author Administrator
 *
 */
public class Demo08 {
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
			String name = "dashen";
			//��ȷ�������ǣ�thisispassword
			String password = "thisispassword";
			String sql = "select * from user where name = '" + name +"' and password = '" + password+"'";
			ResultSet rs = s.executeQuery(sql);
			if(rs.next())
				System.out.println("�˺�������ȷ");
			else
				System.out.println("�˺��������");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

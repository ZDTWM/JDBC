package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *	����ʹ������
 */
public class Demo17 {
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
            // �������ǰ����
            // �������еĶ��������Ҫô���ɹ���Ҫô��ʧ��
			c.setAutoCommit(false);
			
			//��Ѫ��SQL
			String sql1 = "update hero set hp = hp + 1 where id = 2";
			s.execute(sql1);
			
			//��Ѫ��SQL
			//��С��д��д���� updata(����update)
			String sql2 = "updata hero set hp = hp -1 where id = 2";
			s.execute(sql2);
			//�ֶ��ύ
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

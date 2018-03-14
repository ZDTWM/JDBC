package jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * ʹ��try-with-resource�ķ�ʽ�Զ��ر�����
 * @author Administrator
 *
 */
public class Demo15 {
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
		)
		{
			//�鿴���ݿ�����Ԫ����
			//�����ݿ�������汾�������汾��������Щ���ݿ�ȵ�
			DatabaseMetaData dbmd = c.getMetaData();
			
			// ��ȡ���ݿ��������Ʒ����
			System.out.println("���ݿ��Ʒ���ƣ�" + dbmd.getDatabaseProductName());
			// ��ȡ���ݿ��������Ʒ�汾��
			System.out.println("���ݿ��Ʒ�汾��" + dbmd.getDatabaseProductVersion());
			// ��ȡ���ݿ�������������ͱ���֮��ķָ��� ��test.user
			System.out.println("���ݿ�ͱ�ָ�����" + dbmd.getCatalogSeparator());
			//��������
			System.out.println("�������ƣ�" + dbmd.getDriverName());
			// ��ȡ�����汾
			System.out.println("�����汾��" + dbmd.getDriverVersion());
			
			System.out.println("�������ݿ��б�");
			ResultSet rs = dbmd.getCatalogs();
			while(rs.next()){
				System.out.println("���ݿ����ƣ�" + rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

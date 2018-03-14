package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * �����������ȡ��������id
 * @author Administrator
 *
 */
public class Demo14 {
	public static void main(String[] args) {
		
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
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		)
		{
			ps.setString(1, "����");
			ps.setFloat(2, 616);
			ps.setInt(3, 100);
			//ִ�в������
			ps.execute();
            // ��ִ�����������MySQL��Ϊ�²�������ݷ���һ��������id
            // JDBCͨ��getGeneratedKeys��ȡ��id
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				System.out.println(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

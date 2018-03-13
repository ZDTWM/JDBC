package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ѯ����
 * @author Administrator
 *
 */
public class Demo06 {
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
				//��ѯ��SQL
				String sql = "select * from hero";
				// ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
				ResultSet rs = s.executeQuery(sql);		
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString(2);
					float hp = rs.getFloat("hp");
					int damage = rs.getInt(4);
					System.out.printf("%d\t%s\t%f\t%d%n",id,name,hp,damage);
				}
		         // ��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
	            // rs.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

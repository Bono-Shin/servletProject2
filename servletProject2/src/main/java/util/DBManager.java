package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {

	// static���� �δ� ������ �Ź� ��üȭ ���� ��������
	public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	public static String user = "system";
	public static String pass = "1234";
	
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url,user,pass);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	//�����ε� ���
	public static void close(PreparedStatement psmt, Connection conn, ResultSet rs) {
		try {
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
			if(rs != null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

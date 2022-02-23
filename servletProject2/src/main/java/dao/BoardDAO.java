package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBManager;
import vo.BoardVO;

public class BoardDAO {

	public ArrayList<BoardVO> list(HttpServletRequest request, HttpServletResponse response){
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from board";
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setSubject(rs.getString("subject"));
				vo.setWriter(rs.getString("writer"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			
		}
		
		return alist;
	}
}

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
				vo.setBidx(rs.getInt("bidx"));
				
				alist.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(psmt, conn, rs);
		}
		
		return alist;
	}
	
	public BoardVO oneSelect(String bidx) {
		BoardVO vo = new BoardVO();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "select * from board where bidx = "+bidx;
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setSubject(rs.getString("subject"));
				vo.setWriter(rs.getString("writer"));
				vo.setContent(rs.getString("content"));
				vo.setBidx(rs.getInt("bidx"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(psmt, conn, rs);
		}
		
		return vo;
	}
	
	public void update(BoardVO vo) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "update board set " 
					+ "writer = ?, content = ?, "
					+ "subject = ? "
					+ "where bidx = "+vo.getBidx();
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWriter());
			psmt.setString(2, vo.getContent());
			psmt.setString(3, vo.getSubject());
			
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(psmt, conn, rs);
		}
	}
	
	public void insert(BoardVO vo) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "insert into board(BIDX,subject,writer,content,midx,hit) values(BIDX_SEQ.NEXTVAL,?,?,?,27,0)";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getSubject());
			psmt.setString(2, vo.getWriter());
			psmt.setString(3, vo.getContent());
			
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(psmt, conn, rs);
		}
	}
	
	public void delete(String bidx) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "delete from board where bidx="+bidx;
			psmt = conn.prepareStatement(sql);
			
			psmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			DBManager.close(psmt, conn, rs);
		}
	}
}

package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.BoardVO;

/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, String[] commands) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*
		��� 1
		if(commands[1].equals("list.do")) {
			ArrayList<BoardVO> alist = list();
			
			request.setAttribute("alist", alist);
			RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
			rd.forward(request, response);
		}
		*/
		
		//��� 2
		if(commands[1].equals("list.do")) {
			list(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//�� ��Ʈ�ѷ������� ȣ���ϱ� ����
	/*
	��� 1
	private ArrayList<BoardVO> list(){
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		//DAO ȣ�� �ʿ�
		
		return alist;
	}
	*/
	
	//��� 2
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		//DAO ȣ�� �ʿ�
		
		
		request.setAttribute("alist", alist);
		RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
		rd.forward(request, response);
		
		
	}
}

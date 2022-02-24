package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
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
		방법 1
		if(commands[1].equals("list.do")) {
			ArrayList<BoardVO> alist = list();
			
			request.setAttribute("alist", alist);
			RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
			rd.forward(request, response);
		}
		*/
		
		//방법 2
		if(commands[1].equals("list.do")) {
			list(request, response);
			
		}else if(commands[1].equals("view.do")) {
			view(request, response);
			
		}else if(commands[1].equals("viewModify.do")) {
			if(request.getMethod().equals("GET")) {
				modify(request, response);
				
			}else if(request.getMethod().equals("POST")) {
				update(request, response);
			}	
		}else if(commands[1].equals("insert.do")) {
			if(request.getMethod().equals("GET")) {
				insertPage(request, response);
			}else if(request.getMethod().equals("POST")) {
				insert(request, response);
			}
		}else if(commands[1].equals("delete.do")) {
			delete(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//이 컨트롤러에서만 호출하기 위해
	/*
	방법 1
	private ArrayList<BoardVO> list(){
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		//DAO 호출 필요
		
		return alist;
	}
	*/
	
	//방법 2
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BoardVO> alist = new ArrayList<>();
		
		//session 호출하는 방법
		//HttpSession session = request.getSession();
		//session.setAttribute()로도 사용 할 수 있다.
		
		//DAO 호출 필요
		BoardDAO boardDAO = new BoardDAO();
		alist = boardDAO.list(request, response);
		
		request.setAttribute("alist", alist);
		RequestDispatcher rd = request.getRequestDispatcher("/board/list.jsp");
		rd.forward(request, response);
		
		
	}
	
	private BoardVO view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidx = request.getParameter("bidx");
		
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = boardDAO.oneSelect(bidx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/board/view.jsp");
		rd.forward(request, response);
		
		return vo;
	}
	
	private BoardVO modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String bidx = request.getParameter("bidx");
		
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = boardDAO.oneSelect(bidx);
		
		request.setAttribute("vo", vo);
		RequestDispatcher rd = request.getRequestDispatcher("/board/viewModify.jsp");
		rd.forward(request, response);
		
		return vo;
		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		
		vo.setBidx(Integer.parseInt(request.getParameter("bidx")));
		vo.setSubject(request.getParameter("subject"));
		vo.setWriter(request.getParameter("writer"));
		vo.setContent(request.getParameter("content"));
		
		BoardDAO boardDAO = new BoardDAO();
		
		boardDAO.update(vo);
		
		response.sendRedirect(request.getContextPath()+"/board/view.do?bidx="+vo.getBidx());
		
	}
	
	private void insertPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/insert.jsp");
		rd.forward(request, response);
		
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		
		vo.setSubject(request.getParameter("subject"));
		vo.setWriter(request.getParameter("writer"));
		vo.setContent(request.getParameter("content"));
		
		BoardDAO boardDAO = new BoardDAO();
		
		boardDAO.insert(vo);
		
		response.sendRedirect(request.getContextPath()+"/board/list.do");
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidx = request.getParameter("bidx");
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.delete(bidx);
		
		response.sendRedirect(request.getContextPath()+"/board/list.do");
		
	}
}

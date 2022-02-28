<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	//ArrayList<BoardVO> alist = (ArrayList<BoardVO>)request.getAttribute("alist");

	//el문 중복시키기 - page가 최종이므로 testpage가 출력된다.
	pageContext.setAttribute("test","testpage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${login.name }님 환영합니다.
	<h2>board 목록 ${test }</h2>
	
	<table border="1">
		<thead>
			<tr>
				<th>글제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${alist}" var="vo">
			<tr>
				<td><a href="view.do?bidx=${vo.bidx }">${vo.subject }</a></td>
				<td>${vo.writer }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<button onclick="location.href='insert.do'">등록</button>
</body>
</html>
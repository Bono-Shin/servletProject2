<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.util.*" %>    
<%@ page import = "vo.*" %>
<%
	ArrayList<BoardVO> alist = new ArrayList();
	for(int i=0; i<10; i++){
		BoardVO vo = new BoardVO();
		vo.setBidx(1);
		vo.setContent("내용입니다");
		vo.setSubject(i+"번째 제목입니다");
		vo.setWriter("작성자"+i);
		alist.add(vo);
	}
	
	pageContext.setAttribute("alist",alist);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수 선언 및 셋팅 -->
	<c:set var="test" value="100" />
	
	<!-- scope 설정 (session = 모든 페이지에서 사용)-->
	<c:set var="test" value="100" scope="session" />
	
	
	<!-- 출력 -->
	${test+10}
	
	<!-- 문자열을 붙이고 싶으면 -->
	${test }10
	
	<c:set var="test1" value="200" />
	${test1 }
	
	<!-- el문과 조합 -->
	<c:set var="result" value="${test1+100 }" />
	${result }
	
	<c:set var="result" value="${test1+100>100 }" />
	${result }
	
	<c:set var="test" value="<h2>test</h2>" />
	${test }
	<c:out value="${test }" />
	
	<c:out value="" default="no data" />
	
	<!-- attribute가 삭제 되는 것이다 -->
	<c:remove var="test" />
	<c:out value="${test }" />
	
	<!-- else문이 없다 -->
	<c:if test="${10>5 }">
		10은 5보다 큽니다.
	</c:if>
	
	<!-- swich문처럼 아래로 계속 출력하진 않음 -->
	<c:set var="test" value="30"/>
	
	<c:choose>
		<c:when test="${test>30 }">
			test는 30보다 큽니다.
		</c:when>
		<c:when test="${test<30 }">
			test는 30보다 작습니다.
		</c:when>
		<c:otherwise>
			test는 30과 같습니다.
		</c:otherwise>
	</c:choose>
	
	<!-- 반복문 -->
	<c:forEach begin="1" end="10" var="cnt" step="2" >
		${cnt }번째 반복합니다.<br>
	</c:forEach>
	
	<br>
	
	<!-- 알아서 alist의 처음부터 끝까지 읽음 -->
	<c:forEach items="${alist }" var="boardVO" varStatus="cnt">
		${cnt.count} / ${boardVO.subject } / ${boardVO.content } / ${boardVO.writer }<br>
	</c:forEach>
	
	<!-- format -->
	<c:set var="today" value="<%=new Date() %>"/>
	${today }
	<br>
	<fmt:formatDate value="${today }" type="date" pattern="yyyy/MM/dd(E)"/>
	<br>
	<fmt:formatDate value="${today }" type="time" pattern="hh:mm:ss"/>
	<br>
	<c:set var="num" value="1.2345678"/>
	<fmt:formatNumber value="${num }" pattern="#.00"/>
	<br>
	<fmt:formatNumber value="${1.00 }" pattern="#.##"/>
	
	jstl-1.2.jar 파일을 꼭 넣어야 하고 taglib import 해야함
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	
</body>
</html>
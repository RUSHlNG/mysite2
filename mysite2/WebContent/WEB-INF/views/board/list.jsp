<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    	BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.findAll();
    %>    

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%pageContext.servletContext.contextPath %>/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>	
					<%
					int totalCount = list.size();
					int index = 0;
		 			for(BoardVo vo: list){
					%>				
					<tr>
						<td>[<%=totalCount-index++ %>]</td>
						
						<%
						if(){
						%>
						<img id="profile" style="width:20px" src="<%=request.getContextPath() %>/assets/images/recycle.png">
						<%
						}
						%>
						<td><a href="/board/?a=view&no=<%=vo.getNo()%>">세 번째 글입니다.</a></td>
						<td><%=vo.getUserName() %></td>
						<td><%=vo.getHit() %></td>
						<td><%=vo.getRegDate() %></td>
						<td><a href="/board/?a=delete&no=<%=vo.getNo() %>" class="del">삭제</a></td>
					</tr>
					<%
		 			}
					%>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="/guest/?a=list&p=1">1</a></li>
						<li class="selected">2</li>
						<li><a href="/guest/?a=list&p=3">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<jsp:import url="/WEB-INF/views/includes/navigation.jsp">
		<jsp:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>
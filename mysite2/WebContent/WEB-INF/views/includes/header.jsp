<%@page import="com.bigdata2019.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	UserVo authUser = (UserVo)session.getAttribute("authUser");
%>
<div id="header">
			<h1>MySite</h1>
			<ul>
				<%
					if(authUser == null){
				%>
				<li><a href="<%=request.getContextPath()%>/user?a=Loginform">로그인</a><li>
				<li><a href="<%=request.getContextPath()%>/user?a=joinform">회원가입</a><li>
				<%
					}else{
				%>
				<li><a href="<%=request.getContextPath()%>/user?a=updateform">회원정보수정 </a><li>
				<li><a href="<%=request.getContextPath()%>/user?a=Logout">로그아웃</a><li>
				<li><%=authUser.getName() %>안녕하세요</li>
			<%
					}
			%>
			</ul>
		</div>
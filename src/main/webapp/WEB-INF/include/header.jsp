<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User) session.getAttribute("authentication"); // User로 다운캐스팅해야함
	// 서블릿 3대 저장소 - setAttribyte, getAtrribute 가지고 있음.
	// 1. Application 저장소
	// 2. Request 저장소
	// 3. Session 저장소 -> 보통 (인증, 인가)할 때 사용 <==> 토큰 인증 방식 JWT(Jason Web Token)
	//    Session은 데이터를 서버가 가지고 있고, 토큰은 클라이언트가 데이터를 가지고 있음
	// 클라이언트 저장소
	// 1. cookie
	// 2. localStorage
%>    
    
<header>
	<a href="/ssa/index" class="logo"><h1>LOGO</h1></a>
	<ul class="top-nav">
		<%
			if(user != null) {
		%>
				 <li><%=user.getName() %>님 환영합니다</li>
				 <a href="/ssa/mypage"><li>마이페이지</li></a>
				 <a href="/ssa/logout"><li>로그아웃</li></a>
		 <%
			} else {
		 %>
				 <a href="/ssa/login"><li>로그인</li></a>
				 <a href="/ssa/register"><li>회원가입</li></a>
		 <%
			}
		 %>
	</ul>
</header>
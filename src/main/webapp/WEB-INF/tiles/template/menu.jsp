<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
<!-- 기본 템플릿을 구성할 menu 속성, 템플릿 페이지 menu.jsp 파일 생성 -->
<!-- Book Market, Home, Books, AddBook, Cart 메뉴로 구성 -->
<nav class="navbar navbar-expand navbar-dark bg-dark">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Book Market</a>
		</div>
		<div>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="<c:url value="/home"/>">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/books"/>">Books</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/books/add"/>">Add Book</a></li>
				<li class="nav-item"><a class="nav-link" href="<c:url value="/cart/"/>">Cart</a></li>
				
				<li class="nav-item">
					<sec:authorize access="isAuthenticated()">
						<form:form action="${pageContext.request.contextPath}/logout" method="POST">
							<input type="submit" class="btn btn-success" value="logout"/>
						</form:form>
					</sec:authorize>
				</li>
				
				<li class="nav-item">
					<sec:authorize access="!isAuthenticated()">
						<a class="nav-link" href="<c:url value="/login"/>">Login</a>
					</sec:authorize>
				</li>
				
			</ul>
		</div>
	</div>
</nav>

</body>
</html>
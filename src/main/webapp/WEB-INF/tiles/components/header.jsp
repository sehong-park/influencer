<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page pageEncoding="utf-8"%>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <c:url var="rootUrl" value="/" />
	<a class="navbar-brand" href="${ rootUrl }">Yes24</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
		  <c:url var="booksUrl" value="/books" />
			<li class="nav-item"><a class="nav-link" href="${ booksUrl }">도서</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/carts" />">카트</a></li>
			<li class="nav-item"><a class="nav-link" href="<c:url value="/orders" />">주문</a></li>
			
			<!-- 관리자 페이지 버튼 -->
			<sec:authorize access="hasRole('ADMIN')">
			  <li class="nav-item"><a class="nav-link" href="<c:url value='/admins'/>">관리</a></li>
			</sec:authorize>
		</ul>

		<!-- 로그인 버튼 -->
		<sec:authorize access="isAnonymous()">
			<c:url var="loginUrl" value="/login" />
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${ loginUrl }">로그인</a></li>
			</ul>
		</sec:authorize>

		<!-- 로그아웃 버튼 -->
		<sec:authorize access="isAuthenticated()">
			<c:url var="logoutUrl" value="/logout" />
			<form action="${logoutUrl}" method="post"
				class="navbar-form navbar-right">
				<button type="submit" class="btn btn-default">로그아웃</button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</sec:authorize>
	</div>
</nav>
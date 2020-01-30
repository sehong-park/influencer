<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!-- jumbotron -->
<div class="jumbotron">
	<h1 class="display-4">Index Carts</h1>
	<p class="lead">views/carts/index.jsp</p>
	<hr class="my-4">
	<p>장바구니 페이지</p>
</div>


<div class="container">
	<h2>
		장바구니<span class="badge badge-warning">쇼핑중</span>
	</h2>
	<hr>
	<c:if test="${ fn:length(items) gt 0 }">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th>#</th>
					<th>도서명</th>
					<th>가격</th>
					<th>수량</th>
					<th>합계</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ items }" varStatus="status">
					<tr>
						<th>${ status.count }</th>
						<td>${ item.title }</td>
						<td>${ item.price }</td>
						<td>${ item.amount }</td>
						<td>${ item.price * item.amount }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"></td>
					<td>${ cart.totalPrice }</td>
				</tr>
			</tfoot>
		</table>
		<form action="<c:url value='/orders' />" method="post">
			<input name="id" type="hidden" value="${ cart.id }" type="hidden" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit" class="btn btn-lg btn-block btn-primary">주문하기</button>
		</form>
	</c:if>
</div>

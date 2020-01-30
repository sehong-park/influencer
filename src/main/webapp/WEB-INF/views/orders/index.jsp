<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jumbotron -->
<div class="jumbotron">
	<h1 class="display-4">Orders Index</h1>
	<p class="lead">views/orders/index.jsp</p>
	<hr class="my-4">
	<p>주문 내역을 보여줌</p>
</div>

<div class="container">

	<h2>주문 내역</h2>
	<hr>

	<c:forEach var="order" items="${ orders }">
		<h4>
			<span class="badge badge-warning">출고중</span>
		</h4>
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
				<c:forEach var="item" items="${ order.items }" varStatus="status">
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
					<td>${ order.totalPrice }</td>
				</tr>
			</tfoot>
		</table>
	</c:forEach>
</div>
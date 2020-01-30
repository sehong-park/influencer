<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>
<%@ page pageEncoding="utf-8" session="false"%>

<div class="jumbotron">
	<h1>Books INDEX</h1>
	<p>views/books/index.jsp</p>
	<s:authorize access="hasRole('ADMIN')">
	   <a href="<c:url value="/books/new" />" class="btn btn-lg btn-primary">도서등록</a>
	</s:authorize>
</div>

<div class="container">
  <!-- 검색 -->
	<div class="search">
		<c:url var="booksSearchPath" value="/books/search" />
    <form action="${ booksSearchPath }" method="get">
			<div class="row">
				<div class="col-10">
				  <input name="query" type="text" class="form-control input-lg" id="search" placeholder="도서명으로 검색">
				</div>
				<div class="col-md-2">
					<button type="submit" class="form-control input-lg btn btn-primary">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 도서 -->
	<div class="row">
		<c:forEach var="book" items="${books}" varStatus="status">
			<div class="col-md-4">
				<div class="card">
					<img src="<c:url value="${ book.image }" />" class="card-img-top" />
					<div class="card-body">
						<h3 class="card-title">${ book.title }</h3>
						<p class="card-text">Some quick example text to build on the
							card title and make up the bulk of the card's content.</p>
						<a href="<c:url value='/books/${ book.id }' />" class="btn btn-primary">상세보기</a>
						<s:authorize access="hasRole('ADMIN')">
						  <a href="<c:url value='/books/edit/${ book.id }' />" class="btn btn-info">수정</a>
						  <a href="<c:url value='/books/delete/${ book.id }' />" class="btn btn-danger">삭제</a>
						</s:authorize>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

<script>
$(function() {
    $("#search").autocomplete({
        source : function(request, response) {
            $.ajax({
                type : 'get',
                url : "<c:url value='/api/books/search'/>",
                data : {
                    term : request.term
                },
                success : function(data) {
                    var bookList = data.bookList;
                    response($.map(bookList, function(item) {
                        return item.title;
                    }));
                }
            });
        }
    });
});
</script>
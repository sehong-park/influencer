<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page pageEncoding="utf-8"%>

<div class="jumbotron">
	<h1>${ book.title }</h1>
	<p>${ book.author }저</p>
</div>

<div class="container">

	<div class="row">
		<div class="col-md-4">
	       <img class="card-img-top" src="${ book.image }" alt="Card image cap">
	   </div>
	   <div class="col-md-8">
	       <h3>${ book.title }</h3>
	       <p>저자: ${ book.author }</p>
	       <p>가격: ${ book.price }원</p>
	       <hr class="my-4">
	       <form action="<c:url value='/carts/add' />" method="post">
	           <div class="form-group">
	               <label>수량</label>
	               <input name="amount" class="form-control" type="number" value="1" />
	           </div>
	           <input name="book_id" type="hidden" value="${ book.id }">
	           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	           <button type="submit" class="btn btn-primary">장바구니에 담기</button>
	       </form>
	   </div>
	</div>

	<div class="my-5">
		<h3>리뷰</h3>
	</div>

	<c:if test="${ fn:length(reviews) gt 0 }">
		<table class="table table-stripped" id="reviews">
			<thead>
				<tr>
				  <th>Rating</th>
					<th>User</th>
					<th>Text</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="review" items="${ reviews }">
					<tr>
            <td><c:forEach var="rating" items="${ ratingOptions }" varStatus="status" begin="1" end="${ review.rating }">★</c:forEach></td>
						<td>${ review.user.email }</td>
						<td>${ review.text }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <!-- prev -->
		    <li class="page-item">
		      <a class="page-link" href="<c:url value="/books/${ book.id }?pageNum=1#reviews" />" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    
		    <!-- paging nums -->
		    <c:forEach var="i" begin="${paging.start}" end="${paging.end}" step="1">
		        <li class="page-item" id="review-page-index-${i}"><a class="page-link" href="<c:url value="/books/${ book.id }?pageNum=${i}#reviews" />">${ i }</a></li>
		     </c:forEach>
		    
		    <!-- next -->
		    <li class="page-item">
		      <a class="page-link" href="<c:url value="/books/${ book.id }?pageNum=${paging.lastPageNum}#reviews" />" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</c:if>

	<c:url var="reviewsPath" value="/reviews" />
	<f:form modelAttribute="review" action="${ reviewsPath }" method="post">
		<c:forEach var="error" items="${ fieldErrors }">
			<div class="alert alert-warning">
				<strong>${ error.getField() }</strong>: ${ error.getDefaultMessage() }
			</div>
		</c:forEach>
		<f:textarea path="text" cssClass="form-control" rows="5" />
		
		<!-- 평점 선택창 -->
    <f:label path="rating">평점: </f:label>
    <f:select path="rating">
        <f:options items="${ ratingOptions }"/>
    </f:select>
		
		<f:hidden path="bookId" />
		<f:hidden path="userId" />
		<button class="btn btn-block btn-primary" type="submit">리뷰 등록</button>
	</f:form>

</div>

<script>
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}

var pageNum = $.urlParam('pageNum')
if (pageNum == null) {
	pageNum = 1;
}
$("#review-page-index-" + pageNum).addClass("active");
</script>

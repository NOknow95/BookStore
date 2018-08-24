<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="page_nav">
	<c:set var="begin" value="1"></c:set>
	<c:set var="end" value="5"></c:set>

	<c:choose>
		<c:when test="${page.totalPage<=5 }">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${page.totalPage}"></c:set>
		</c:when>
		<c:when test="${page.pageNumber<=3 }">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="5"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
			<c:set var="end" value="${page.pageNumber+2 }"></c:set>
			<c:if test="${end>page.totalPage }">
				<c:set var="end" value="${page.totalPage }"></c:set>
				<c:set var="begin" value="${end-4 }"></c:set>
			</c:if>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${page.pageNumber==1 }">
			首页 上一页
		</c:when>
		<c:otherwise>
			<a href="${page.path }&pageNumber=1">首页</a>
			<a href="${page.path }&pageNumber=${page.pageNumber-1 }">上一页</a>
		</c:otherwise>
	</c:choose>

	<!-- 遍历页码 -->
	<c:forEach begin="${begin }" end="${end }" var="index">
		<c:choose>
			<c:when test="${page.pageNumber == index }">
				<span style="color: red">[${index}]</span>
			</c:when>
			<c:otherwise>
				<a href="${page.path }&pageNumber=${index }">${index }</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${page.pageNumber==page.totalPage }">
			下一页 末页
		</c:when>
		<c:otherwise>
			<a href="${page.path }&pageNumber=${page.pageNumber+1 }">下一页</a>
			<a href="${page.path }&pageNumber=${page.totalPage }">末页</a>
		</c:otherwise>
	</c:choose>
	共${page.totalPage }页，${page.totalRecord }条记录 到第<input
		value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
		type="button" value="确定" id="pn_btn">
	<script type="text/javascript">
		//为确定按钮绑定点击函数
		$("#pn_btn").click(function() {
			//获取输入的页面
			var pageNumber = $("#pn_input").val();
			//通过修改window.location属性跳转到另一个页面
			window.location = "${page.path }&pageNumber=" + pageNumber;
		});
	</script>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@ include file="/WEB-INF/include/user-info.jsp"%>
	</div>

	<div id="main">

		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>数量</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>

			<c:choose>
				<c:when test=" ${!empty list }">
					<h1>当前用户还没有订单</h1>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="order">
						<tr>
							<td>${order.id }</td>
							<td>
								<%-- 可以使用fmt标签进行日期格式化
									fmt:formDate来格式化一个时间
									value属性指向要格式化的那个时间
									type用来指定时间的类型:
									1、date-->只显示日期，
									2、time-->只显示时间，
									3、both-->都显示
									
									fmt标签可以自动对时间进行国际化支持
								 --%> <fmt:formatDate type="both" value="${order.orderTime }" />
							</td>
							<td>${order.totalCount }</td>
							<td>${order.totalAmount }</td>
							<td><c:choose>
									<c:when test="${order.state==0 }">
										<span>未发货</span>
									</c:when>
									<c:when test="${order.state==1 }">
										<a
											href="client/OrderClientServlet?method=takeBook&orderId=${order.id }">确认收货</a>
									</c:when>
									<c:when test="${order.state==2 }">
										<span>交易完成</span>
									</c:when>
								</c:choose></td>
							<td><a
								href="client/OrderClientServlet?method=orderInfo&orderId=${order.id }">查看详情</a>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>


	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>
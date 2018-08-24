<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
<script type="text/javascript">
	$(function() {

		$("[name=username]").change(function() {
			var url = "${pageContext.request.contextPath }/client/UserServlet";
			var params = {
				"method" : "checkUsername",
				"username" : this.value
			};
			$.post(url, params, function(data) {
				if (data == 0) {
					$(".errorMsg").html("用户名已存在！");
					$("#sub_btn").attr("disabled", true);
				} else if (data == 1) {
					$(".errorMsg").html("用户名可用");
					$("#sub_btn").attr("disabled", false);
				}
			}, "text");
		});

		//点击验证码刷新
		$("#code_img").click(function() {
			this.src = "code.jpg?t=" + Math.random();//加随机数或加自增的数，要src要变化图片才能变
		});
		$("#sub_btn").click(function() {
			var username = $("[name=username]").val();
			var password = $("[name=password]").val();
			var repwd = $("[name=repwd]").val();
			var email = $("[name=email]").val();
			var code = $("[name=code]").val();
			/* alert(username+","+password+","+repwd+","+email+","+code); */
			var namereg = /^[a-zA-Z0-9_-]{3,16}$/;
			if (!namereg.test(username)) {
				alert("请输入包含字母、数字、_、-且3到16位的字符");
				return false;
			}
			var pwdreg = /^[a-z0-9_-]{6,8}$/;
			if (!pwdreg.test(password)) {
				alert("请输入包含字母、数字、_、-且6到8位的字符");
				return false;
			}
			if (password != repwd) {
				alert("两次密码输入有误！");
				return false;
			}
			var emailreg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if (!emailreg.test(email)) {
				alert("请输入正确的邮箱格式！");
				return false;
			}
			if (code == "") {
				alert("请输入验证码！");
				return false;
			}
		});
	});
</script>


<style type="text/css">
.login_form {
	height: 420px;
	margin-top: 25px;
}
</style>
</head>
<body>
	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
	</div>

	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎注册</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册尚硅谷会员</h1>
						<span class="errorMsg">${msg }</span>
					</div>
					<div class="form">
						<form action="client/UserServlet" method="post">
							<input type="hidden" name="method" value="regist" /> <label>用户名称：</label>
							<input value="${param.username }" class="itxt" type="text"
								placeholder="请输入用户名" autocomplete="off" tabindex="1"
								name="username" /> <br /> <br /> <label>用户密码：</label> <input
								class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" /> <br /> <br />
							<label>确认密码：</label> <input class="itxt" type="password"
								placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
							<br /> <br /> <label>电子邮件：</label> <input
								value="${param.email }" class="itxt" type="text"
								placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
								name="email" /> <br /> <br /> <label>验证码：</label> <input
								value="${param.code }" class="itxt" type="text"
								style="width: 150px;" name="code" /> <img id="code_img" alt=""
								src="code.jpg"
								style="float: right; margin-right: 40px ; width: 90px"> <br />
							<br /> <input type="submit" value="注册" id="sub_btn" />

						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>
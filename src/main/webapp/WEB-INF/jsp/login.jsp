<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン | Java Blog</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container p-5">
			<h2 class="mb-5 text-center">ログイン</h2>
			<form action="LoginServlet" method="post">
				<div class="mb-3">
					<label class="form-label">ユーザーID</label> <input type="text" name="userId"
						value="shim" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">パスワード</label> <input type="password" name="pass"
						value="1234" class="form-control">
				</div>
				<input type="submit" name="bt" value="ログイン" class="btn btn-primary">
			</form>
		</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
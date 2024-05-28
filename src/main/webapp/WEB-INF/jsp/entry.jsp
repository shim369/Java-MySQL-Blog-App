<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録 | Java Blog</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
</head>
<body>
	<header class="shadow">
		<nav class="navbar navbar-expand-lg bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand" href="IndexServlet">Java Blog</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav ms-auto mb-2 mb-lg-0">
						<a class="nav-link" href="IndexServlet">ホーム</a>
						<a class="nav-link" href="LoginServlet">ログイン</a>
						<a class="nav-link" href="EntryServlet">ユーザー登録</a>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container p-5">
			<h2 class="mb-5 text-center">ユーザー登録</h2>
			<form action="EntryServlet" method="post">
				<div class="mb-3">
					<label class="form-label">ユーザーID</label> <input type="text"
						name="userId" value="u01" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">パスワード</label> <input type="password"
						name="pass" value="p01" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">メールアドレス</label> <input type="text"
						name="mail" value="u01@gmail.com" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">名前</label> <input type="text" name="name"
						value="u01" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">年齢</label> <input type="text" name="age"
						value="1" class="form-control">
				</div>
				<input
					type="submit" name="bt" value="エントリー" class="btn btn-primary">
			</form>
			<p>
				<c:out value="${errorMsg}" />
			</p>
		</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
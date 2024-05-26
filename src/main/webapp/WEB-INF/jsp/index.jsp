<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Blog</title>
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
				<a class="navbar-brand" href="WelcomeServlet">Java Blog</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav ms-auto mb-2 mb-lg-0">
						<a class="nav-link" href="WelcomeServlet">ホーム</a> <a
							class="nav-link" href="LoginServlet">ログイン</a> <a class="nav-link"
							href="EntryServlet">ユーザー登録</a>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container p-5">
			<h2>ブログ一覧</h2>
			<div class="row">
				<c:forEach var="blog" items="${blogList}">
					<div class="col">
						<div class="card mb-5" style="width: 18rem;">
							<!--<img src="..." class="card-img-top" alt="...">  -->
							<div class="card-body">
								<h5 class="card-title">${blog.title}</h5>
								<p class="card-text">${blog.content}</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" href="css/style.css">
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
						<p class="nav-link">
							ようこそ
							<c:out value="${userId}" />
							さん
						</p>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container p-5">
			<h2 class="mb-5 text-center">ブログを投稿する</h2>
			<form action="PostBlogServlet" method="post" enctype="multipart/form-data">

				<div class="mb-3">
					<label class="form-label">タイトル</label> <input type="text"
						name="title" class="form-control">
				</div>
				<div class="mb-3">
					<label class="form-label">内容</label>
					<textarea name="content" rows="5" cols="50" class="form-control"></textarea>
				</div>
				<div class="mb-3">
					<label for="image" class="form-label">画像</label> <input type="file"
						class="form-control" id="image" name="image_url" required>
				</div>
				<input type="submit" value="投稿" class="btn btn-primary"> <input
					type="hidden" name="userId" value="${userId}">
			</form>
			<h2 class="mt-5 mb-5 text-center">
				<c:out value="${userId}" />
				さんのブログ一覧
			</h2>
			<div class="row">
				<c:forEach var="blog" items="${blogList}">
					<div class="col-12 col-md-4">
						<div class="card mb-5 m-auto">
							<c:choose>
								<c:when test="${empty blog.imageUrl}">
									<img
										src="${pageContext.request.contextPath}/images/dummy.png"
										class="card-img-top custom-img" alt="No Image">
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/${blog.imageUrl}"
										class="card-img-top custom-img" alt="${blog.title}">
								</c:otherwise>
							</c:choose>
							<div class="card-body">
								<h5 class="card-title">${blog.title}</h5>
								<p class="card-text date-icon"><i class="bi bi-clock"></i>${blog.createdAt}</p>
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#modal${blog.id}">
									記事表示</button>

								<!-- モーダルの設定 -->
								<div class="modal fade" id="modal${blog.id}" tabindex="-1"
									aria-labelledby="exampleModalLabel">
									<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5" id="exampleModalLabel">${blog.title}</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="閉じる"></button>
											</div>
											<div class="modal-body">
												<p class="card-text">${blog.content}</p>
											</div>
											<div class="modal-footer">
												<p class="date-icon"><i class="bi bi-clock"></i>${blog.createdAt}</p>
											</div>
											<!-- /.modal-footer -->
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->
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
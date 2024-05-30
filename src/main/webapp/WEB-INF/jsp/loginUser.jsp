<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ブログ投稿 | Java Blog</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<main>
		<div class="container p-5">
			<h2 class="mb-5 text-center">ブログを投稿する</h2>
			<form action="PostBlogServlet" method="post"
				enctype="multipart/form-data">
				<div class="mb-3">
					<label class="form-label">タイトル</label> <input type="text"
						name="title" class="form-control" required>
				</div>
				<div class="mb-3">
					<label class="form-label">内容</label>
					<textarea name="content" rows="5" cols="50" class="form-control" required></textarea>
				</div>
				<div class="mb-3">
					<label for="image" class="form-label">画像</label> <input type="file"
						class="form-control" id="image" name="image_url">
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
									<img src="${pageContext.request.contextPath}/images/dummy.png"
										class="card-img-top custom-img" alt="No Image">
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/${blog.imageUrl}"
										class="card-img-top custom-img" alt="${blog.title}">
								</c:otherwise>
							</c:choose>
							<div class="card-body">
								<h5 class="card-title">${blog.title}</h5>
								<p class="card-text date-icon">
									<i class="bi bi-clock"></i>${blog.createdAt}</p>
								<button type="button" class="btn btn-primary"
									data-bs-toggle="modal" data-bs-target="#modal${blog.id}">
									編集</button>
								<button type="button" class="btn btn-danger"
									onclick="confirmDelete(${blog.id})">削除</button>
								<!-- モーダルの設定 -->
								<div class="modal fade" id="modal${blog.id}" tabindex="-1"
									aria-labelledby="exampleModalLabel">
									<div
										class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
										<div class="modal-content">
											<div class="modal-header">
												<h1 class="modal-title fs-5" id="exampleModalLabel">
													編集フォーム</h1>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="閉じる"></button>
											</div>
											<div class="modal-body">
												<form action="UpdateBlogServlet" method="post"
													enctype="multipart/form-data">
													<input type="hidden" name="id" value="${blog.id}">
													<input type="hidden" name="userId" value="${blog.userId}">
													<input type="hidden" name="existingImageUrl"
														value="${blog.imageUrl}">
													<div class="mb-3">
														<label for="title" class="form-label">タイトル：</label> <input
															type="text" name="title" id="title" class="form-control"
															value="${blog.title}">
													</div>
													<div class="mb-3">
														<label for="content" class="form-label">内容：</label>
														<textarea name="content" id="content" class="form-control">${blog.content}</textarea>
													</div>
													<div class="mb-3">
														<label for="imageUrl" class="form-label">画像URL：</label> <input
															type="file" name="image_url" id="imageUrl"
															class="form-control" onchange="previewImage(event)">
														<div class="mt-2">
															<label for="previewImage" class="form-label">現在の画像：</label>
															<img id="previewImage"
																src="<c:if test='${not empty blog.imageUrl}'>${pageContext.request.contextPath}/${blog.imageUrl}</c:if>
            <c:if test='${empty blog.imageUrl}'>${pageContext.request.contextPath}/images/dummy.png</c:if>"
																alt="Current Image" class="img-thumbnail">
														</div>
													</div>
													<div>
														<button type="submit" class="btn btn-success">更新</button>
													</div>
												</form>

											</div>
											<div class="modal-footer">
												<p class="date-icon">
													<i class="bi bi-clock"></i>${blog.createdAt}</p>
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
	<script>
    function confirmDelete(blogId) {
        if (confirm("本当に削除しますか？")) {
            deleteBlog(blogId);
        }
    }

    function deleteBlog(blogId) {
        fetch('DeleteBlogServlet?blogId=' + blogId, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('削除に失敗しました。');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('削除に失敗しました。');
        });
    }
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="beans.Account" %>
<%
Account account = (Account)request.getAttribute( "account" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了 | Java Blog</title>
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
		<h2 class="mb-5 text-center">ユーザー登録</h2>
			<c:choose>
				<c:when test="${account != null}">
					<p>ユーザー登録に成功しました</p>
					<a href="PostBlogServlet?userId=${sessionScope.userId}">${sessionScope.userId}さんのページへ</a>
				</c:when>
				<c:otherwise>
					<p>ユーザー登録に失敗しました</p>
					<a href="EntryServlet">ユーザー登録画面へ</a>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
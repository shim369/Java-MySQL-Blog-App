<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                    <%
                        String userId = (String) session.getAttribute("userId");
                        if (userId == null) {
                    %>
                    <a class="nav-link" href="LoginServlet">ログイン</a>
                    <a class="nav-link" href="EntryServlet">ユーザー登録</a>
                    <%
                        } else {
                    %>
                    <a class="nav-link" href="PostBlogServlet?userId=<%= userId %>">
                        <c:out value="<%= userId %>" /> さん
                    </a>
                    <a class="nav-link" href="LogoutServlet">ログアウト</a>
                    <%
                        }
                    %>
				</div>
			</div>
		</div>
	</nav>
</header>
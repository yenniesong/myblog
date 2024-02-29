<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
    <form action="/auth/loginProc" method="post"> <%--옛날 방식 json으로 변경해준다.--%>
        <div class="form-group">
            <label for="username">User name</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <button id="btn-login" class="btn btn-primary">Login</button>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=40ac2e83f30991656f90eacf46bfdf33&redirect_uri=http://localhost:8096/auth/kakao/callback">
            <img height="38px" src="${pageContext.request.contextPath}/image/kakao_login_button.png" />
        </a>
    </form>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>




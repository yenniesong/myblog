<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">
<%--    <form action="/user/join" method="post"> 옛날 방식 json으로 변경해준다. --%>
    <form>
        <div class="form-group">
            <label for="username">User name</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>
    </form>
    <button id="btn-login" class="btn btn-primary">Login</button>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp"%>




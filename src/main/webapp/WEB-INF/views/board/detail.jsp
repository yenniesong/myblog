<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp"%>

<div class="container">

    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>

    <c:if test="${board.user.id == principal.user.id}">
        <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>

    <br/><br/>

    <div>
        글 번호 : <span id="id"><i>${board.id} </i></span>
        작성자 : <span><i>${board.user.username} </i></span>
    </div>
    <br/>

    <div>
<%--            <label for="title">Title</label>--%>
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div>
<%--            <label for="content">Content</label>--%>
        <div>${board.content}</div>
    </div>
    <hr/>

    <div class="card">
        <form>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button id="btn-reply-save" type="button" class="btn btn-primary">write</button>
            </div>
        </form>
    </div>
    <br />
    <div class="card">
        <div class="card-header">reply list</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">writer : ${reply.user.username} &nbsp;</div>
                        <button class="badge">delete</button>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

<script>
    $('.summernote').summernote({
        // placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@include file="../layout/footer.jsp"%>




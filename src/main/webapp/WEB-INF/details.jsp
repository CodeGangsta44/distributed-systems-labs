<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>TODO</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body style="width: 80%; margin: auto">
<nav class="navbar navbar-dark bg-dark" style="margin-top: 15px">
    <div class="container-fluid">
        <a class="navbar-brand">ToDo App</a>
        <a href="/" class="btn btn-outline-light">Back to Home</a>
    </div>
</nav>
<div style="width:50%; margin: auto; margin-top: 20px">
    <div class="card">
        <h5 class="card-header">${todo.name}</h5>
        <div class="card-body">
            <form action="/todo/${todo.id}" method="post">
                <c:choose>
                    <c:when test="${not empty todo.entries}">
                        <h5 class="card-title">Entries:</h5>
                        <c:forEach items="${todo.entries}" var="entry">
                            <div class="input-group mb-3">
                                <div class="input-group-text">
                                    <input type='hidden' name="uuidForUpdate${entry.id}" value='off'>
                                    <input class="form-check-input mt-0" name="uuidForUpdate${entry.id}" type="checkbox" ${entry.status ? 'checked' : ''} aria-label="Checkbox for following text input" />
                                </div>
                                <input type="text" class="form-control" disabled aria-label="Text input with checkbox" value="${entry.value}" />
                                <button type="submit" class="btn btn-danger" name="delete" value="${entry.id}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                        <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                    </svg>
                                </button>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h5 class="card-title">No list entries yes...</h5>
                    </c:otherwise>
                </c:choose>
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Value </span>
                    <input type="text" name="newValue" class="form-control" placeholder="Value" aria-label="Value" aria-describedby="basic-addon1">
                    <button type="submit" class="btn btn-success" name="add">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/>
                        </svg>
                        Add
                    </button>
                </div>
                <button class="btn btn-primary" type="submit" name="update">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
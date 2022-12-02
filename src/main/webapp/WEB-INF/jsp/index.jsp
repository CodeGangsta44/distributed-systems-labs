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
        <form class="d-flex" action="/" method="post" style="margin-bottom: 0">
            <input class="form-control me-2" type="text" name="name" placeholder="ToDo List Name" aria-label="Create">
            <button class="btn btn-outline-light" type="submit">Create!</button>
        </form>
    </div>
</nav>
<c:choose>
    <c:when test="${not empty lists}">
        <div class="row row-cols-3">
            <c:forEach items="${lists}" var="list">
                <div class="col" style="margin-top: 20px">
                    <div class="card">
                        <div class="card-header">
                            ${list.name}
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${not empty list.entries}">
                                    <c:forEach items="${list.entries}" var="entry">
                                        <p class="card-text">
                                            <c:choose>
                                                <c:when test="${entry.status}">
                                            <span class="text-success">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
                                                    <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
                                                </svg>
                                            </span>
                                                </c:when>
                                                <c:otherwise>
                                            <span class="text-secondary">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dash-lg" viewBox="0 0 16 16">
                                                    <path fill-rule="evenodd" d="M2 8a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11A.5.5 0 0 1 2 8Z"/>
                                                </svg>
                                            </span>
                                                </c:otherwise>
                                            </c:choose>
                                            ${entry.value}
                                        </p>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <h5 class="card-title">No list entries yes...</h5>
                                </c:otherwise>
                            </c:choose>
                            <form action="/delete" method="post" style='display:inline;'>
                                <input type="text" name="id" hidden value="${list.id}">
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form>
                            <a href="/todo/${list.id}" class="btn btn-primary">Edit</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="card text-center" style="width: 50%; margin: auto; border: 0; margin-top: 20px">
            <div class="card-body">
                <h1 class="display-5 text-secondary">Please, create your first ToDo list to start using the app</h1>
                <p class="text-secondary">
                    <svg xmlns="http://www.w3.org/2000/svg" width="300" height="300" fill="currentColor" class="bi bi-list-check" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3.854 2.146a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 3.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 7.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z"/>
                    </svg>
                </p>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<div>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
        font:normal normal normal 16px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
    }

    th, td {
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even){background-color: #f2f2f2}

    th {
        background-color: rgb(137, 173, 216);
        color: white;
    }

    td {
        padding-left: 15px;
    }

    .error {
        font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
        color: rgb(255, 109, 109);
        padding-left: 25px;
    }

    .table-button {
        background-color: lightgray;
        /* color:white; */
        padding: 8px 20px;
        /* margin: 15px 0 0 0; */
        border: none;
        cursor: pointer;
        font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
    }

    .button {
        background-color: rgb(137, 173, 216);
        color: white;
        border: none;
        cursor: pointer;
        padding: 8px 42px;
        margin: 25px;
        display: inline-block;
        width: 150px;
        font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
    }

    .current-user {
        font:normal normal normal 16px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
        color: rgb(113, 149, 194);
        padding-left: 25px;
        padding-top: 25px;
    }
</style>
</div>

<body>
<div>
    <table>
        <tr>
            <th>Username</th>
            <th>Name</th>
            <th>Edit</th>
            <th>Remove</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td width="30%">${user.username}</td>
                <td width="30%">${user.name}</td>
                <td width="20%">
                    <form action="/users" method="post">
                        <input type="hidden" name="user" value="<c:out value="${user.username}" />" />
                        <input type="submit" value="Edit" name="edit" class="table-button"/>
                    </form>
                </td>
                <td width="20%">
                    <form action="/users" method="post">
                    <input type="hidden" name="user" value="<c:out value="${user.username}" />"/>
                    <input type="submit" value="Remove" name="remove" onclick="return confirm('Are you sure you want remove this user?')" class="table-button" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<div class="current-user">
    <p> Current user: ${currentUser} </p>
</div>

<div>
    <form action="/users" method="post">
        <input type="submit" value="Add user" name="add" class="button">
        <input type="submit" value="Logout" name="logout" class="button">
    </form>
</div>

<div class="error">
    <p> ${error} </p>
</div>

</body>
</html>


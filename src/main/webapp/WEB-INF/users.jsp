<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <h2>User list</h2>
        <p>${error}</p>
        <form action="/users" method="post">
            <input type="submit" value="Add user" name="add">
        </form>

        <table id="users" border="1">
            <tr>
                <th>Username</th>
                <th>Name</th>
            </tr>

            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user[0]}</td>
                    <td>${user[1]}</td>
                    <td>
                        <form action="users" method="post">
                            <input type="hidden" name="user" value="<c:out value="${user[0]}" />" />
                            <input type="submit" value="Remove" name="remove" onclick="return confirm('Are you sure you want remove this user?')">
                        </form>
                    </td>
                    <td>
                        <form action="users" method="post">
                            <input type="hidden" name="user" value="<c:out value="${user[0]}" />" />
                            <input type="submit" value="Edit" name="edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <p>
            Current user: ${currentUser}
        </p>

        <form action="users" method="post">
            <input type="submit" value="Logout" name="logout">
        </form>

    </body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <h2>User list</h2>
        <form action="/users" method="post">
            <input type="submit" value="Add user" name="add">
        </form>

        <table id="users" border="1">
            <tr>
                <th>Username</th>
            </tr>

            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user}</td>
                    <td>
                        <form action="users" method="post">
                            <input type="hidden" name="user" value="<c:out value="${user}" />" />
                            <input type="submit" value="Remove" name="remove">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

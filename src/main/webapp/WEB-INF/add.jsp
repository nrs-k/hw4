<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <body>
        <h2>Add User</h2>
        <p>${error}</p>
        <form action="/add" method="post">
            Username: <br>
            <input type="text" name="username" />
            <br>
            Password: <br>
            <input type="password" name="password">
            <br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
    </body>
</html>
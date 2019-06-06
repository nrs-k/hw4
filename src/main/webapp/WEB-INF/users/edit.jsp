<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<h2>Edit user information</h2>
<p>${error}</p>
<form action="/users/edit" method="post">
    Username: <br>
    <input type="text" name="newUsername" placeholder=${currentUsername}>
    <br>
    Name: <br>
    <input type="name" name="newName" placeholder=${currentName}>
    <br><br>
    <input type="submit" value="Save" name="save" >
    <input type="submit" value="Cancel" name="cancel">
</form>
</body>
</html>

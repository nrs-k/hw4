<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<div>
    <style>
        .header {
            font: normal normal bold 20px/1.4em opensanshebrewcondensed-regular,sans-serif ;
            color:rgb(137, 173, 216);
            text-align: center;
        }

        .error {
            font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
            color: rgb(255, 109, 109);
        }

        .button {
            background-color: rgb(137, 173, 216);
            color: white;
            border: none;
            cursor: pointer;
            padding: 8px 42px;
            margin: 15px;
            display: inline-block;
            width: 135px;
            font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
        }

        .textbox {
            display:flex;
            margin: 25px 0 25px 0;
        }
        .input-textbox {
            font:normal normal normal 14px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
            border-style:solid;
            border-color:rgba(145, 145, 145, 1);
            margin: 0;
            flex:1;
        }
        .input-textbox::placeholder {color:#7F808A;}
        .input-textbox:hover {
            background-color:rgba(242, 250, 249, 1);
            border-color:rgba(82, 82, 82, 1);
        }
    </style>
</div>

<div id="layout">
    <style>
        .container {
            display: grid;
            grid-template-columns: 1fr;
            height: 1000px;
            align-content: center;
        }

        .block {
            display: flex;
            align-items: center;
            justify-content: center;
        }

    </style>
</div>

<body>
<div class="container">
    <div class="block header">
        <h2>Edit user information</h2>
    </div>
    <div>
        <form action="/users/edit" method="post">
            <div class="block">
                <div class="textbox" style="width:300px;height:43px;">
                    <input type="text" name="newName" placeholder=${currentName} style="padding-left:14px" class="has-custom-focus input-textbox">
                </div>
            </div>
            <div class="block">
                <input type="submit" class="button" value="Save" name="save"></input>
                <input type="submit" class="button" value="Cancel" name="cancel"></input>
            </div>
        </form>
    </div>

    <div class="error block">
        <p> ${error} </p>
    </div>
</div>
</body>
</html>
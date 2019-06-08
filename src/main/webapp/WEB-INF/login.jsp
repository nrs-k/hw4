<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link rel="stylesheet" type="text/css" href="https://static.parastorage.com/services/santa-resources/resources/viewer/user-site-fonts/v7/languages.css">

<div id="content">
    <style>
        .header {
            font: normal normal bold 60px/1.4em opensanshebrewcondensed-regular,sans-serif ;
            color:rgb(137, 173, 216);
            line-height: 1.1px;
            letter-spacing:0.1em;
            text-align: center;
            margin: 0 0 0 0;
        }

        .textbox {
            display:flex;
            margin: 25px 0 0 0;
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

        .error {
            font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
            color: rgb(255, 109, 109);
            margin: 0 0 0 0;
        }

        .button {
            background-color: rgb(137, 173, 216);
            color: white;
            border: none;
            cursor: pointer;
            padding: 8px 52px;
            margin: 25px;
            font:normal normal normal 15px/1.4em avenir-lt-w01_35-light1475496,sans-serif;
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
        <div class="header block">
            <h1>Login</h1>
        </div>
        <div>
            <form action="/login" method="post">
                <div class="block">
                    <div class="textbox" style="width:304px;height:43px;">
                        <input type="text" name="username" style="padding-left:14px" placeholder="username" class="input-textbox">
                    </div>
                </div>
                <div class="block">
                    <div class="textbox" style="width:304px;height:43px;">
                        <input type="text" name="password" style="padding-left:14px" placeholder="password" class="input-textbox">
                    </div>
                </div>
                <div class="block">
                    <input type="submit" class="button" value="Login"></input>
                </div>
            </form>
        </div>
        <div class="error block">
            <p> ${error} </p>
        </div>
    </div>

</body>
</html>


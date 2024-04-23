<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Website/Sign Up</title>
    <style>
        *{
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            overflow: hidden;
            font-family: Arial, Helvetica, sans-serif;
        }

        body{
            background-color: rgb(245, 245, 245);
        }

        .container{
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            margin: auto;
            width: 400px;
            height: 100vh;
            background-color: white;
            border-right: 1px solid black;
            border-left: 1px solid black;
        }

        .container h1{
            margin-bottom: 1rem;
            font-size: 2.5rem;

        }

        .container form{
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            width: 100%;
        }

        .container .labels{
            display: flex;
            flex-direction: column;
            margin-bottom: 1.5rem;
            width: 80%;
            font-size: 1.75rem;
        }

        .container .labels label{
            margin-bottom: .5rem;
        }

        .container .labels input{
            padding: .5rem;
            font-size: 1.5rem;
            outline: none;
        }

        .container .signButton{
            padding: .5rem .25rem;
            width: 30%;
            font-size: 1.5rem;
            color: white;
            border: none;
            border-radius: 10px;
            background-color: rgb(0, 0, 229);
            cursor: pointer;
        }

        .container .signButton:active{
            background-color: white;
            color: rgb(0, 0, 229);
            border: 1px solid black;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>SIGN UP</h1>
    <hr>
    <form action="/signUp" method="post">
        <div class="labels">
            <label for="email">E-Mail</label>
            <input type="email" name="email" id="email">
        </div>
        <div class="labels">
            <label for="firstName">First Name</label>
            <input type="text" name="firstName" id="firstName">
        </div>
        <div class="labels">
            <label for="lastName">Last Name</label>
            <input type="text" name="lastName" id="lastName">
        </div>
        <div class="labels">
            <label for="password">Password</label>
            <input type="password" name="password" id="password">
        </div>
        <div class="labels">
            <label for="phone">Phone-Number</label>
            <input type="text" name="phone" id="phone">
        </div>
        <input type="submit" value="SIGN UP" class="signButton">
    </form>
</div>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            overflow: hidden;
            font-family: Arial, Helvetica, sans-serif;
        }

        body{
            background-color: rgb(245, 245, 245);
        }

        .container{
            background-color: white;
            border-right: 1px solid black;
            width: 50vw;
            height: 100vh;
        }

        .container .user{
            display: flex;
            flex-direction: row;
            align-items: center;
            padding: 1rem;
        }

        .container .user .img{
            margin-right: 2rem;
            background-color: grey;
            width: 75px;
            height: 75px;
            border-radius: 50%;
        }

        .container .informations{
            margin-top: 1rem;
            margin-left: 2rem;
        }

        .container .informations .names{
            display: flex;
            flex-direction: row;
            gap: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="user">
        <div class="img"><img src="" alt=""></div>
        <h1 class="email">${userName.email}</h1>
    </div>
    <hr>
    <div class="informations">
        <div class="names">
            <h2 class="name">First Name: ${userName.firstName}</h2>
            <h2 class="name">Last Name: ${userName.lastName}</h2>
        </div>
    </div>
    <a href="/user/${userName.userId}/articles">Articles</a>
    <a href="/user/${userName.userId}/userArticles">Add Article</a>
    <a href="/files/${userName.userId}/upload">Add Post</a>
</div>
</body>
</html>
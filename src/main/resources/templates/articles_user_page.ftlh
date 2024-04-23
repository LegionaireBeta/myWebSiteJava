<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>



</head>
<body>

<script>
    let refetch = () => {
        $.ajax({
            type: "GET",
            url: "/user/${userId}/arti",
            success: function(response) {
                renderTable(response, $('#myArticles'))
            },
            dataType: "json",
            contentType: "application/json"
        })
    }

    function renderTable(articles, table) {
        let innerHtml = `<tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Text</th>
                             </tr>`;
        for (let i = 0; i < articles.length; i++) {
            innerHtml += '<tr>';
            innerHtml += '  <td>' + articles[i]['articleId'] + '</td>';
            innerHtml += '  <td>' + articles[i]['name'] + '</td>';
            innerHtml += '  <td>' + articles[i]['type'] + '</td>';
            innerHtml += '  <td>' + articles[i]['text'] + '</td>';
            innerHtml += '</tr>';
        }

        table.html(innerHtml);
    }


    function sendUser(name, type, text) {

        event.preventDefault();

        let data = {
            "name": name,
            "type": type,
            "text": text
        };



        $.ajax({
            type: "POST",
            url: "/user/${userId}/addArticles",
            data: JSON.stringify(data),
            success: refetch,
            dataType: "json",
            contentType: "application/json"
        });
    }
</script>


<div>
        <input name="name" type="text" id="name" placeholder="name">
        <input name="type" type="text" id="type" placeholder="type">
        <input name="text" type="text" id="text" placeholder="text">
        <input type="submit" value="Add" onclick="sendUser($('#name').val(),
                              $('#type').val(),
                              $('#text').val())">
</div>

<div>
    <table id="myArticles">
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>TEXT</th>
        </tr>
        <#list articles as article>
            <tr>
                <td>${article.articleId}</td>
                <td>${article.name}</td>
                <td>${article.type}</td>
                <td>${article.text}</td>
            </tr>
        </#list>
    </table>
</div>

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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>

        *{
            box-sizing: border-box;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
            padding: 0;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .like-cell {
            display: flex;
            align-items: center;
        }

        .like-icon {
            border: none;
            background: none;
            cursor: pointer;
            font-size: 20px;
            color: #4CAF50;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .like-icon:hover {
            color: #087f23;
        }

        span {
            margin-left: 8px;
            font-size: 18px;
        }
    </style>

</head>
<body>

<div>
    <input type="text" id="searchText" placeholder="SEARCH">
    <button id="searchButton">ADD</button>
    <table id="articlesTable">
        <tr>
            <th>Article</th>
        </tr>
        <#list articles as article>
            <tr>
                <td>${article.articleId}</td>
                <td>${article.name}</td>
                <td>${article.text}</td>

                <td>

                            <button type="submit" class="like-icon">
                                <i class="fas fa-thumbs-up"></i>
                            </button>
                            <span>${article.likes}</span>

                </td>
            </tr>
        </#list>
    </table>

    <div class="pagination">
        <button id="prevPage">Previous</button>
        <span id="currentPage">1</span>
        <button id="nextPage">Next</button>
    </div>
</div>

<script>

    var currentPage = parseInt(document.getElementById('currentPage').textContent);
    var size = 3;
    var searchText = "empty";

    newPage(currentPage);

    document.getElementById('prevPage').addEventListener('click', function() {
        if (currentPage > 1) {
            currentPage -= 1;
            document.getElementById('currentPage').textContent = currentPage;
            newPage(currentPage);
        }
    });

    document.getElementById('nextPage').addEventListener('click', function() {
        currentPage += 1;
        document.getElementById('currentPage').textContent = currentPage;
        newPage(currentPage);
    });

    function newPage(page){
        $.ajax({
            type: "GET",
            url: "/searchArticle?page="+ (page - 1) + "&size="+size+"&q="+searchText,
            success:function (response){
                renderTable(response, $('#articlesTable'))
            },
            error: function(xhr, status, error) {
                console.error("An error occurred while fetching the data: " + error);
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
            innerHtml += '  <td>'
            innerHtml +=        '<button type="submit" class=like-icon>'
            innerHtml +=            '<i class="fas fa-thumbs-up"></i>'
            innerHtml +=        '</button>'
            innerHtml +=        '<span>' + articles[i]['likes'] + '</span>'
            innerHtml +=    '</td>'
            innerHtml += '</tr>';
        }


        table.html(innerHtml);

        document.getElementById('searchButton').addEventListener('click', function (){
            currentPage = 1
            searchText = $('#searchText').val();

            $.ajax({
                type: "GET",
                url: "/searchArticle?page="+ (currentPage - 1) + "&size="+size+"&q="+ searchText,
                success:function (response){
                    renderTable(response, $('#articlesTable'))
                },
                error: function(xhr, status, error) {
                    console.error("An error occurred while fetching the data: " + error);
                },
                dataType: "json",
                contentType: "application/json"
            })
        })
    }

</script>

</body>
</html>


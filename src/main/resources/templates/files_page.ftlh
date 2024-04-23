<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Website/Sign Up</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <script
            src="https://code.jquery.com/jquery-3.5.1.js"
            integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
            crossorigin="anonymous"></script>

    <style>
        *{
            box-sizing: border-box;
        }
        .container{
            display: flex;
            flex-wrap: wrap;
            gap: 30px;
        }

        .file_container{
            position: relative;
            width: 200px;
            flex: 1 0 21%;
            border: 1px solid black;
            border-radius: 10px;
        }

        .file_container img{
            padding: 1rem 0;
            width: 100%;
            border-bottom: 1px solid black;
        }

        .file_container .like_container{
            position: absolute;
            right: 4px;
            bottom: 4px;
        }
    </style>

</head>
<body>
    <div class="container">
        <#list files as file>
            <div class="file_container">
                <img src="http://localhost:8080/files/${file.getStorageFileName()}">
                <p>${file.getDescription()}</p>
                <div class="like_container">
                    <span class="like_count"  data-file-id="${file.fileId}">${file.getLikes()}</span>
                    <button class="like_button" onclick="sendLike(${file.fileId})"><i class="fas fa-thumbs-up"></i></button>
                </div>
            </div>

        </#list>
    </div>


    <script>
        let refetch = () => {
            $.ajax({
                type: "GET",
                url: "/files/likes",
                success: function(response) {
                    renderTable(response)
                },
                dataType: "json",
                contentType: "application/json"
            })
        }

        function renderTable(files) {
            files.forEach(file => {
                let likeSpan = $('.like_count[data-file-id="' + file.fileId + '"]');
                if (likeSpan) {
                    likeSpan.text(file.likes);
                }
            });
        }

        function sendLike(fileId) {
            $.ajax({
                type: "POST",
                url: "/files/${userId}/liked/"+fileId+"/s",
                data: JSON.stringify(fileId),
                success: refetch,
                dataType: "json",
                contentType: "application/json"
            });
        }
    </script>
</body>
</html>
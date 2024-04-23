<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <title>My Website/Sign Up</title>


    <style>

        #photo {
            width: 200px;
        }



    </style>
    <script>
        function sendFile() {
            let formData = new FormData();
            let files = ($('#file'))[0]['files'];
            let description = $('input[name="description"]').val();
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            formData.append("description", description);

            $.ajax({
                type: "POST",
                url: "/files/${userId}",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    let fileUrl = 'http://localhost:8080/files/' + response;
                    $('#photo').append('<img src = "' + fileUrl + '"/>');
                })
                .fail(function () {
                    alert('Error')
                });
        }
    </script>

</head>
<body>
    <div>
        <input type="file" id="file" name="file" placeholder="file's name.../">
        <input type="hidden" id="file_hidden">
        <input type="text" id="description" name="description" placeholder="DESCRIPTION">
        <button onclick="sendFile()">Upload file</button>
        <div class="filename"></div>
    </div>
    <hr>
    <div id="photo"></div>
</body>
</html>
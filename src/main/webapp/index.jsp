<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>João Arruda - Lucas Fusinato</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="resources/comportamentos.js"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>


<%--    <link rel="stylesheet" href="resources/estilos.css">--%>
    <style>

        body {
            /*background: #555;*/
        }

        .content {
            /*max-width: 500px;*/
            margin: auto;
            background: white;
            padding: 10px;
        }

    </style>
</head>
<body id="body">
    <div>
        <label for="key">Key: </label>
        <input placeholder="key" id="key">
        <label for="token">Token: </label>
        <input placeholder="token" id="token">
        <button type="button" class="btn btn-success" onclick="loadBoard({key: document.querySelector('#key').value, token: document.querySelector('#token').value})">CONFIRMAR</button>
    </div>
    <br>
    <div aria-label="" class="content form-select">
        <label for="quadros">Selecione um quadro: </label>
        <select onchange="" id="quadros" name="quadros"></select>
        <button type="button" class="btn btn-success" onclick="redirectBoard({id:document.getElementById('quadros').value, nome:document.getElementById('quadros').options[document.getElementById('quadros').selectedIndex].text, key: document.querySelector('#key').value, token: document.querySelector('#token').value, 'shortUrl': document.getElementById('quadros').options[document.getElementById('quadros').selectedIndex].shortUrl})">Selecionar</button>
        <button type="button" class="btn btn-danger" onclick="postBoardDelete({nome:document.getElementById('quadros').value, key: document.querySelector('#key').value, token: document.querySelector('#token').value})">Excluir quadro</button>
        <span>ou crie um novo: </span>
        <input placeholder="nome" id="newQuadro">
        <button type="button" class="btn btn-primary" onclick="postBoard({nome:document.getElementById('newQuadro').value, key: document.querySelector('#key').value, token: document.querySelector('#token').value})">Adicionar novo quadro</button>
    </div>

<script>
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const key = urlParams.get('key');
    const token = urlParams.get('token');
    document.querySelector('#key').value = key;
    document.querySelector('#token').value = token;
    loadBoard({key: document.querySelector('#key').value, token: document.querySelector('#token').value})
</script>
</body>
</html>
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
<body onload="loadBoard()" id="body">
    <div aria-label="" class="content form-select">
        <label for="quadros">Selecione um quadro: </label>
        <select onchange="" id="quadros" name="quadros"></select>
        <button type="button" class="btn btn-success" onclick="redirectBoard({id:document.getElementById('quadros').value, nome:document.getElementById('quadros').options[document.getElementById('quadros').selectedIndex].text})">Selecionar</button>
        <button type="button" class="btn btn-danger" onclick="postBoardDelete({nome:document.getElementById('quadros').value})">Excluir quadro</button>
        <span>ou crie um novo: </span>
        <input placeholder="nome" id="newQuadro">
        <button type="button" class="btn btn-primary" onclick="postBoard({nome:document.getElementById('newQuadro').value})">Adicionar novo quadro</button>
    </div>
</body>
</html>
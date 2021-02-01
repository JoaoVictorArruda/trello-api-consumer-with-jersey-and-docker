const packageName = '/trabalho_a3';

let draggedCard;

function loadBoard(parametros) {
    $.ajax({
        url: packageName + "/board-servlet",
        data: parametros
    }).done(function(response) {
        let selectQuadros = document.querySelector("#quadros");
        for (let i = 0; i < response.length; i++){
            let opt = document.createElement('option');
            opt.value = response[i].id;
            opt.innerHTML = response[i].name;
            opt.shortUrl = response[i].shortUrl;
            selectQuadros.appendChild(opt);
        }
    }).fail(function(error) {
        document.getElementById('body').innerHTML = error.responseText;
    });
}

function postBoard(parametros) {
    $.ajax({
        url: packageName + "/board-servlet",
        type: "POST",
        data: parametros
    }).done(function(response) {
        document.getElementById('newQuadro').value = "";
        window.location.reload(true);
    }).fail(function(error) {
        document.getElementById('body').innerHTML = error.responseText;
    });
}

function postBoardDelete(parametros) {
    parametros.delete = true;
    $.ajax({
        url: packageName + "/board-servlet",
        type: "POST",
        data: parametros
    }).done(function(response) {
        document.getElementById('newQuadro').value = "";
        window.location.reload(true);
    }).fail(function(error) {
        document.getElementById('body').innerHTML = error.responseText;
    });
}


function redirectBoard(parametros) {
    window.location.href = packageName+ "/main.jsp?"+ $.param(parametros);
}

function loadList(parametros) {
    const onClickFunctionAddNewList = (mouseEvent) => {
        const botao = mouseEvent.target;
        const nome = prompt('Digite o nome da nova list:');
        $.ajax({
            url: packageName + "/list-servlet",
            data: {idBoard: botao.idBoard, nome: nome, 'key': key, 'token': token},
            type: "POST",
        }).done(function(response) {
            window.location.reload(true);
        }).fail(function(error) {
            document.getElementById('body').innerHTML = error.responseText;
        });
    }

    const onClickDeleteList = (mouseEvent) => {
        if(confirm('Voce quer excluir a lista selecionada?')) {
            const list = mouseEvent.target;
            parametros = {delete: true, id: list.idList, 'key': key, 'token': token};
            $.ajax({
                url: packageName + "/list-servlet",
                type: "POST",
                data: parametros
            }).done(function(response) {
                window.location.reload(true);
            }).fail(function(error) {
                document.getElementById('body').innerHTML = error.responseText;
            });
        }
    };

    const onDropUpdateCard = (event) => {
        let list = event.target;
        $.ajax({
            url: packageName + "/card-servlet",
            data: {idCard: draggedCard, idList: list.idList, move:true, 'key': key, 'token': token},
            type: "POST",
        }).done(function(response) {
            window.location.reload(true);
        }).fail(function(error) {
            document.getElementById('body').innerHTML = error.responseText;
        });
    };

    const allowDrop = (event) => {
        event.preventDefault();
    }

    $.ajax({
        url: packageName + "/main-servlet",
        data: parametros
    }).done(function(response) {
        for (let i = 0; i < response.length; i++) {
            let listDoc = document.createElement('div');
            listDoc.classList.add('list');
            listDoc.id = 'id_'+response[i].id;
            listDoc.idList = response[i].id;
            listDoc.ondragover = allowDrop;
            listDoc.ondrop = onDropUpdateCard;
            let listTitleDoc = document.createElement('h3');
            listTitleDoc.classList.add('list-title');
            listTitleDoc.innerHTML = response[i].name;
            listTitleDoc.idList = response[i].id;
            listTitleDoc.onclick = onClickDeleteList;
            listDoc.appendChild(listTitleDoc);

            loadCardsFromList(response[i]);

            document.getElementById('lists-container').appendChild(listDoc);

        }
        let buttonAddNewList = document.createElement('button');
        buttonAddNewList.classList.add('btn');
        buttonAddNewList.classList.add('add-list-btn');
        buttonAddNewList.idBoard = parametros.id;
        buttonAddNewList.innerHTML = "Adicionar uma lista";
        buttonAddNewList.onclick = onClickFunctionAddNewList;
        document.getElementById('lists-container').appendChild(buttonAddNewList);
    }).fail(function(error) {
        document.getElementById('body').innerHTML = error.responseText;
    });
}

function loadCardsFromList(parametros) {
    const onClickFunctionAddNewCard = (mouseEvent) => {
        const botao = mouseEvent.target;
        const nome = prompt('Digite o nome do novo card:');
        $.ajax({
            url: packageName + "/card-servlet",
            data: {list: botao.idList, nome: nome, 'key': key, 'token': token},
            type: "POST",
        }).done(function(response) {
            window.location.reload(true);
        }).fail(function(error) {
            document.getElementById('body').innerHTML = error.responseText;
        });
    }

    const onClickDeleteCard = (mouseEvent) => {
        if(confirm('Voce quer excluir o card selecionado?')) {
            const card = mouseEvent.target;
            $.ajax({
                url: packageName + "/card-servlet",
                type: "POST",
                data: {delete: true, id: card.idCard, 'key': key, 'token': token}
            }).done(function(response) {
                window.location.reload(true);
            }).fail(function(error) {
                document.getElementById('body').innerHTML = error.responseText;
            });
        }
    };

    const drag = (event) => {
        event.dataTransfer.setData("Text", event.currentTarget.idCard);
        draggedCard = event.currentTarget.idCard;
    }

    $.ajax({
        url: packageName + "/main-servlet",
        data: {list: parametros.id, 'key': key, 'token': token}
    }).done(function(response) {
        let cardList = document.createElement('ul');
        cardList.classList.add('list-items');
        for (let j = 0; j < response.length; j++) {
            let card = document.createElement('li');
            card.innerHTML = response[j].name;
            card.idCard = response[j].id;
            card.idList = response[j].idList;
            card.idBoard = response[j].idBoard;
            card.onclick = onClickDeleteCard;
            card.ondragstart = drag;
            card.draggable = true;
            cardList.appendChild(card);
        }
        let listButtonAddNewCard = document.createElement('button');
        listButtonAddNewCard.classList.add('btn');
        listButtonAddNewCard.classList.add('add-card-btn');
        listButtonAddNewCard.innerHTML = "Adicionar um card";
        listButtonAddNewCard.idList = parametros.id;
        listButtonAddNewCard.idBoard = parametros.idBoard;
        listButtonAddNewCard.onclick = onClickFunctionAddNewCard;
        listButtonAddNewCard.id = 'button_'+parametros.id;
        document.getElementById('id_' + parametros.id).appendChild(cardList);
        document.getElementById('id_' + parametros.id).appendChild(listButtonAddNewCard);
    }).fail(function(error) {
        document.getElementById('body').innerHTML = error.responseText;
    });
}
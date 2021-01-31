<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/estilos.css">
<script src="resources/comportamentos.js"></script>

<script>

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const id = urlParams.get('id')
    const nome = urlParams.get('nome')

    loadList({'id': id});
</script>

<!-- Masthead -->
<header class="masthead">

    <div class="boards-menu">

        <button onclick="window.location.href = packageName+ '/index.jsp'" class="boards-btn btn"><i class="fab fa-trello boards-btn-icon"></i>Boards</button>

        <div class="board-search">
            <input type="search" id="board-search" class="board-search-input" aria-label="Board Search">
            <i class="fas fa-search search-icon" aria-hidden="true"></i>
        </div>

    </div>

    <div class="logo">

        <h1><i class="fab fa-trello logo-icon" aria-hidden="true"></i>Trello</h1>

    </div>

    <div class="user-settings">

        <button class="user-settings-btn btn" aria-label="Create">
            <i class="fas fa-plus" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="Information">
            <i class="fas fa-info-circle" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="Notifications">
            <i class="fas fa-bell" aria-hidden="true"></i>
        </button>

        <button class="user-settings-btn btn" aria-label="User Settings">
            <i class="fas fa-user-circle" aria-hidden="true"></i>
        </button>

    </div>

</header>
<!-- End of masthead -->


<!-- Board info bar -->
<section class="board-info-bar">

</section>
<!-- End of board info bar -->

<!-- Lists container -->
<section id="lists-container" class="lists-container">


</section>

<body id="body"></body>
<script>

    document.getElementById('board-search').value = nome;

</script>
let players = [];

async function verFuncao() {

    if (players.length > 0) {
        await fetch("http://10.80.40.41:8080/search/" + players.pop())
            .then((response) => response.json())
            .then(data => {
                document.getElementById("goto").style.display = "none";
                document.getElementById("next").style.opacity = 1;
                document.getElementById("info").style.opacity = 1;                
                document.getElementById("funcaoPlayer").style.display = "block";
                document.getElementById("funcaoPlayer").src = "image/" + data['minhaPosicao'] + ".png";

                document.getElementById("funcao").innerHTML = data['minhaPosicao'].split("_").join(" ");
                document.getElementById("observar").innerHTML = data['observar'];
                document.getElementById("lista").innerHTML = data['players'].map(p => p.name).join(", ");
              
            });
    }
}

function next() {   
    document.getElementById("goto").style.display = "block";    
    document.getElementById("funcaoPlayer").style.display = "none";            
    document.getElementById("next").style.opacity = 0;
    document.getElementById("info").style.opacity = 0;

    if (players.length < 1) {
        document.getElementById("show").style.opacity = 1;
        document.getElementById("goto").style.display = "none";                
        document.getElementById("player").innerHTML = "Aguarde";
        return;
    }

    var name = players[players.length - 1];
    document.getElementById("player").innerHTML = name;
   
}

async function initGame() {
    await fetch("http://10.80.40.41:8080/init", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(
            {
                "players": [
                    "Victor",
                    "Joanderson",
                    "Gabriel",
                    "Maurinho",
                    "Glenda"
                ]
            }
        )
    }).then(function(res){ console.log(res) });
       
}

async function allPlayers() {   

    await fetch("http://10.80.40.41:8080/allPlayers")
        .then((response) => response.json())
        .then(data => {
            players = data;
            next();
        });
}

async function reveal() {   

    await fetch("http://10.80.40.41:8080/reveal")
        .then((response) => response.json())
        .then(data => {
            data.forEach(p => {
                createItemList(p['function'], p.name);                 
            }); 

            document.getElementById("lista").innerHTML = data['players']
            .map(p => p.name).join(",");
              
        });
}

function show(id) {
    document.getElementById(id).style.opacity = 1;
}

function hide(id) {
    document.getElementById(id).style.opacity = 0;
}

function createItemList(funcao, nome) {
    const imageUrl = "image/" + funcao + ".png";

    const divHeader = document.createElement("div");
    divHeader.classList.add("header");
    divHeader.innerHTML = nome;

    const divContent = document.createElement("div");
    divContent.classList.add("content");
    divContent.appendChild(divHeader);

    const img = document.createElement("img");
    img.src = imageUrl;
    img.classList.add("ui");
    img.classList.add("avatar");
    img.classList.add("image");

    const divItem = document.createElement("div");
    divItem.classList.add("item");
    divItem.appendChild(img);
    divItem.appendChild(divContent);   

    document.getElementById("reveal").appendChild(divItem);
}
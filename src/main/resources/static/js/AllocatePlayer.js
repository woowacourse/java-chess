export const allocatePlayer = () => {
    axios.get("/allPlayer")
    .then(response => assemblePlayers(response))
    .catch(error => console.log(error));
}

export const deleteAllPlayer = () => {
    const $playerList = document.querySelector(".playerList");
    while ($playerList.hasChildNodes()) {
        $playerList.removeChild($playerList.firstChild);
    }
}

const assemblePlayers = (response) => {
    deleteAllPlayer();
    const receivePlayers = response.data.players;
    const $playerList = document.querySelector(".playerList");
    receivePlayers.forEach((receivePlayer) => {
        foamOnePlayer($playerList, receivePlayer);
    })
}

const foamOnePlayer = ($playerList, receivePlayer) => {
    let packedPlayerButton = document.createElement("button");
    packedPlayerButton.setAttribute("id", receivePlayer.playerIndex);
    packedPlayerButton.setAttribute("class", "playerLink " + receivePlayer.playerName);
    packedPlayerButton.innerText = receivePlayer.playerName;
    $playerList.appendChild(packedPlayerButton);
}
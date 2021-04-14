export const addGameLink = () => {
    const $playerSlot = document.querySelector(".playerList");
    $playerSlot.addEventListener("click", function (e) {
        const targetPlayer = e.target;
        if (targetPlayer && targetPlayer.className.includes("playerLink")) {
            movePage(targetPlayer);
        }
    })
}

const movePage = (targetPlayer) => {
    const index = targetPlayer.id;
    window.location.href = "./play/"+index;
}

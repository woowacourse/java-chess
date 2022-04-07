const onSuccessResponse = ({id, found}) => {
    if (found) {
        window.location.replace(`/game/${id}`);
        return;
    }
    alert(`${id}에 해당되는 게임은 아직 만들어지지 않았습니다!`)
}

const searchAndRedirect = async (event) => {
    event.preventDefault();
    const response = await fetch(event.target.action, {
        method: 'post',
        body: new URLSearchParams(new FormData(event.target))
    });
    const {ok, error, body} = await response.json();
    if (!ok) {
        alert(error);
        return;
    }
    onSuccessResponse(body);
}

const init = () => {
    const form = document.querySelector("form");
    form.addEventListener('submit', searchAndRedirect);

}

init();

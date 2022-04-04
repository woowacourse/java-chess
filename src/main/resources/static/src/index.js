let source = '';
let target = '';

function move(id) {
    if (source === '') {
        let elementById = document.getElementById(id);
        elementById.style.backgroundColor = "#FF0000";
        source = id;
        return;
    }

    if (target === '') {
        let elementById = document.getElementById(id);
        elementById.style.backgroundColor = "#FF0000";
        target = id;

        fetch("/move", {
            method: "POST",
            headers: {
                "Content-Type": "text/plain"
            },
            body: source + " " + target
        }).then((res) => {
            console.log(res);
        })
        document.getElementById(source).style.backgroundColor = '';
        elementById.style.backgroundColor = '';
        source = '';
        target = '';
        location.reload();
    }
}

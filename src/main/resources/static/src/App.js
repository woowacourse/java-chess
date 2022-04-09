let source = null;
let target = null;

function start() {
    fetch('/start', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify(null)
    }).then(response => response.json())
    .then(response => {
        if (parseInt(response.status) !== 200) {
            alert(response.message);
            location.replace("/");
        }
        location.replace("/play");
    });
}

function end() {
    fetch('/end')
        .then(response => response.json())
        .then(response => {
            if (parseInt(response.status) !== 200) {
                alert(response.message);
            }
            location.replace("/");
        });
}

function selectBlock(id) {
    if (source == null) {
        source = id;
        return;
    }
    target = id;
    move(source, target)
}

function move(source, target) {
    const request = {
        source: source.id,
        target: target.id
    }

    reinitialize();

    fetch('/move', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify(request)
    }).then(response => response.json())
        .then(response => {
            if (response.status !== 200) {
                alert(response.message);
            }
            location.replace("/play")
        });
}

function reinitialize() {
    source = null;
    target = null;
}

let source = null;
let target = null;

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
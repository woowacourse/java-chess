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

    console.log(request);
    reinitialize();

    let res = fetch('/move', {
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
        });
    sleep();
    location.replace("/play")
}

function sleep() {
    const wakeUpTime = Date.now() + 100;
    while (Date.now() < wakeUpTime) {
    }
}

function reinitialize() {
    source = null;
    target = null;
}
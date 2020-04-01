function choose(value) {
    if (document.getElementById('source').value !== '') {
        chooseDestination(value);
    } else {
        chooseSource(value);
    }
}

function chooseSource(sourceValue) {
    document.getElementById('source').value = sourceValue;
    document.getElementById(sourceValue).classList.add('source');
}

function chooseDestination(destinationValue) {
    var redpoint = document.getElementsByClassName('destination').item(0);
    if (redpoint) {
        redpoint.classList.remove('destination');
    }
    document.getElementById('destination').value = destinationValue;
    document.getElementById(destinationValue).classList.add('destination');
}
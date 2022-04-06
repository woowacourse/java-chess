function makeForm() {
    let form = document.createElement('form');
    form.action = document.location.href + '/move';
    form.method = 'POST';
    document.body.append(form);
    form.appendChild(makeInputInForm());
    return form;
}

function makeInputInForm() {
    let input = document.createElement("input");
    input.type = "hidden";
    input.name = "command";
    input.value = movePositions.start + "," + movePositions.end;
    return input;
}

function clicked(position) {
    if (movePositions.start === '') {
        movePositions.start = position;
    } else {
        movePositions.end = position;
        let form = makeForm();
        form.submit();

        movePositions.start = '';
        movePositions.end = '';
    }
}

const movePositions = {
    start : '',
    end : ''
}
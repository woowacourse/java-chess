console.log('script 읽는중')
const body = document.getElementsByTagName('body')[0]
body.addEventListener('click', onClick)
let squareIdList = [];

const GAME_NAME = '1';

function onClick(event) {
    let target = event.target;
    console.log(target.tagName + '클릭됨')
    if (target.tagName === 'BUTTON') {
        onButtonClick();
    }

    if (target.classList.contains('piece-image') || target.classList.contains('cell')) {
        if (squareIdList.length !== 2) {
            squareIdList.push(onCellClick());
            postTwoCells();
        }
    }

    function onCellClick() {
        console.log(target.closest("td").id)
        target.closest("td").classList.toggle("clicked")
        return target.closest("td").id
    }

    function postTwoCells() {
        if (squareIdList.length === 2) {
            makeAllCellsNotClicked()
            let id = location.href.split("/").pop();
            postForm('/move/' + id, squareIdList)
        }
    }

    function makeAllCellsNotClicked() {
        for (const element of document.getElementsByClassName('clicked')) {
            element.toggleAttribute('clicked')
        }
    }

    function postForm(url, elements) {
        let form = makeForm();
        let toInput = makeHiddenInput('to');
        let fromInput = makeHiddenInput('from');

        form.appendChild(fromInput);
        form.appendChild(toInput);
        document.body.appendChild(form)
        form.submit()

        function makeForm() {
            let form = document.createElement('form')
            form.setAttribute('method', 'post')
            form.setAttribute('action', url)
            document.characterSet = 'utf-8'
            return form;
        }

        function makeHiddenInput(name) {
            let toInput = document.createElement('input')
            toInput.type = 'hidden'
            toInput.name = name
            toInput.value = elements.pop()
            return toInput;
        }
    }

    function onButtonClick() {
        const classList = target.classList
        let id = location.href.split("/").pop();
        if (classList.contains('start-button')) {
            location.href = "/new-board/" + id
        }
        if (classList.contains('status-button')) {
            location.href = "/status/" + id
        }
        if (classList.contains('board-button')) {
            location.href = "/board/" + id
        }
        if (classList.contains('end-button')) {
            location.href = '/game-end/' + id
        }
        if (classList.contains('referrer-button')) {
            location.href = document.referrer;
        }
        if (classList.contains('referrer-start-button')) {
            location.href = "/new-board/" + document.referrer.split("/").pop();
        }
    }
}

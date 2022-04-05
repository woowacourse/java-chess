console.log('script 읽는중')
const body = document.getElementsByTagName('body')[0]
body.addEventListener('click', onClick)
let squareIdList = [];

function onClick(event) {
    let target = event.target;
    console.log(target.tagName + '클릭됨')
    if (target.tagName === 'BUTTON') {
        onButtonClick();
    }

    function postTwoCells(clickedCellCount) {
        if (squareIdList.length === 2) {
            postForm('/move', squareIdList)
            makeAllCellsNotClicked()
        }
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

    function makeAllCellsNotClicked() {
        for (const element of document.getElementsByClassName('clicked')) {
            element.toggleAttribute('clicked')
        }
    }

    function postForm(url, elements) {
        let form = document.createElement('form')
        form.setAttribute('method', 'post')
        form.setAttribute('action', url)
        document.characterSet = 'utf-8'
        let toInput = document.createElement('input')
        toInput.type = 'hidden'
        toInput.name = 'to'
        toInput.value = elements.pop()
        let fromInput = document.createElement('input')
        fromInput.type = 'hidden'
        fromInput.name = 'from'
        fromInput.value = elements.pop()
        form.appendChild(fromInput);
        form.appendChild(toInput);
        document.body.appendChild(form)
        form.submit()
    }

    function onButtonClick() {
        const classList = target.classList
        if (classList.contains('start-button')) {
            location.href = "/new-board"
        }
        if (classList.contains('status-button')) {
            location.href = "/status"
        }
    }

}

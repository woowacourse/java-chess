console.log('script 읽는중')
const body = document.getElementsByTagName('body')[0]
body.addEventListener('click', onClick)

function onClick(event) {
    let target = event.target;
    console.log(target.tagName + '클릭됨')
    if (target && target.tagName === 'BUTTON') {
        onButtonClick();
    }

    function onButtonClick() {
        const classList = target.classList
        if (classList.contains('start-button')) {
            location.href = "/board"
        }
        if (classList.contains('status-button')) {
            location.href = "/status"
        }
    }

}

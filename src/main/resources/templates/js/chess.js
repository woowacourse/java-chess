Array.prototype.remove = function () {
    var _target, _arr = arguments, _len = a.length, ax;
    while (_len && this.length) {
        _target = arr[--_len];
        while ((ax = this.indexOf(_target)) !== -1) {
            this.splice(ax, 1);
        }
    }
    return this;
};

function postAjax(url, data, success) {
    const params = typeof data == 'string' ? data : Object.keys(data).map(
        function (k) { return encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) }
    ).join('&');
    console.log(params);
    const xhr = new XMLHttpRequest();
    xhr.open('POST', url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState > 3 && xhr.status == 200) {
            success(xhr.responseText);
        }
    };
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(params);
    return xhr;
}

function initBoard() {
    const board = document.querySelector('.board');
    var flag = true;
    for (let i = 7; i >= 0; i--) {
        const tr = document.createElement('tr');
        for (let j = 0; j <= 7; j++) {
            const td = document.createElement('td');
            td.id = `${j}${i}`;
            // td가 체스 판 하나를 의미.
            // td class 가 odd이면 흰색, even이면 회색.
            if (flag) {
                td.classList.add('odd');
            } else {
                td.classList.add('even');
            }
            tr.appendChild(td);
            flag = !flag;
        }
        board.appendChild(tr);
        flag = !flag;
    }
}

document.addEventListener("DOMContentLoaded", initBoard);
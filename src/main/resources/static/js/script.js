function clickEvent(event) {
    const sourceText = document.getElementById("source");
    const targetText = document.getElementById("target");

    if (sourceText.value !== '' && targetText.value !== '') {
        document.getElementById("source").value = '';
        document.getElementById("target").value = '';
    }

    if (sourceText.value === '') {
        sourceText.value = event.target.id;
        return;
    }
    targetText.value = event.target.id;
    document.getElementById('moveform').submit();
}

for (let row = 8; row >= 1; row--) {
    let alphabet = 'abcdefgh'.split('');
    for (let alpha of alphabet) {
        document.getElementById(alpha+row).addEventListener("click", event=>clickEvent(event));
    }
}

const Confettiful = function(el) {
    this.el = el;
    this.containerEl = null;

    this.confettiFrequency = 3;
    this.confettiColors = ['#EF2964', '#00C09D', '#2D87B0', '#48485E','#EFFF1D'];
    this.confettiAnimations = ['slow', 'medium', 'fast'];

    this._setupElements();
    this._renderConfetti();
};

Confettiful.prototype._setupElements = function() {
    const containerEl = document.createElement('div');
    const elPosition = this.el.style.position;

    if (elPosition !== 'relative' || elPosition !== 'absolute') {
        this.el.style.position = 'relative';
    }

    containerEl.classList.add('confetti-container');

    this.el.appendChild(containerEl);

    this.containerEl = containerEl;
};

Confettiful.prototype._renderConfetti = function() {
    this.confettiInterval = setInterval(() => {
        const confettiEl = document.createElement('div');
        const confettiSize = (Math.floor(Math.random() * 3) + 7) + 'px';
        const confettiBackground = this.confettiColors[Math.floor(Math.random() * this.confettiColors.length)];
        const confettiLeft = (Math.floor(Math.random() * this.el.offsetWidth)) + 'px';
        const confettiAnimation = this.confettiAnimations[Math.floor(Math.random() * this.confettiAnimations.length)];

        confettiEl.classList.add('confetti', 'confetti--animation-' + confettiAnimation);
        confettiEl.style.left = confettiLeft;
        confettiEl.style.width = confettiSize;
        confettiEl.style.height = confettiSize;
        confettiEl.style.backgroundColor = confettiBackground;

        confettiEl.removeTimeout = setTimeout(function() {
            confettiEl.parentNode.removeChild(confettiEl);
        }, 3000);

        this.containerEl.appendChild(confettiEl);
    }, 25);
};

window.confettiful = new Confettiful(document.querySelector('.js-container'));
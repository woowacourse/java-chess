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
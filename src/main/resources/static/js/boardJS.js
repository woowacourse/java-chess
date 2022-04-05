function printStatus() {
    fetch("/status")
        .then(async (response) => {
            let status = document.getElementById("status");
            let text = await response.text();
            status.innerText = text;
        })
}
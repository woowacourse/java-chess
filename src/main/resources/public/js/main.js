document.querySelectorAll(".position").forEach(element => {
    element.addEventListener("click", async function () {
        if (document.getElementById("moveStart").value) {
            document.getElementById("moveEnd").value = element.id;
            document.getElementById("moveCommand").value = "이동";
            document.getElementById("moveForm").submit();
        } else {
            document.getElementById("movableStart").value = element.id;
            document.getElementById("movableCommand").value = "이동체크";
            document.getElementById("movableForm").submit();
        }
    });
});
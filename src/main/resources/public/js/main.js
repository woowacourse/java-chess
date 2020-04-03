let startPosition;

document.querySelectorAll(".position").forEach(element => {
    element.addEventListener("click", async function () {
        document.getElementById("moveStart").value = element.id;
        document.getElementById("movableStart").value = element.id;
        document.getElementById("movableCommand").value = "이동체크";
        document.getElementById("movableForm").submit();
    });
});
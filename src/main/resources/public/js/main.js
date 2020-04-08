document.querySelectorAll(".position").forEach(element => {
    element.addEventListener("click", async function () {
        if (document.getElementById("moveStart").value) {
            document.getElementById("moveEnd").value = element.id;
            document.getElementById("moveCommand").value = "이동";
        } else {
            document.getElementById("moveStart").value = element.id;
            document.getElementById("moveCommand").value = "이동체크";
        }
        document.getElementById("moveForm").submit();
    });
});
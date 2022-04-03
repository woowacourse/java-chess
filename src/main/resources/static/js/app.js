const dragables = document.querySelectorAll(".draggable");
const containers = document.querySelectorAll(".square");

let fromVariable = null;
let toVariable = null;

dragables.forEach((dragable) => {
    dragable.addEventListener("dragstart", () => {
        fromVariable = dragable.parentElement.id;

        dragable.classList.add("dragging");
    });

    dragable.addEventListener("dragend", () => {
        toVariable = dragable.parentElement.id;

        dragable.classList.remove("dragging");

        const form = document.createElement("form");

        form.setAttribute("method", "post");
        form.setAttribute("action", "/move");
        form.appendChild(createInput("from", fromVariable));
        form.appendChild(createInput("to", toVariable));
        document.body.appendChild(form);
        form.submit();
    });
});

containers.forEach((container) => {
    container.addEventListener("dragover", (e) => {
        e.preventDefault();
        const draggable = document.querySelector(".dragging");
        container.appendChild(draggable);
    });
});

function createInput(name, value) {
    const input = document.createElement("input");

    input.setAttribute("type", "hidden");
    input.setAttribute("name", name);
    input.setAttribute("value", value);

    return input
}
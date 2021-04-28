const SPEED = 0.5;
const SRC_PATH = "/img/";

export class Piece {
  #x
  #y
  #team
  #type
  #component

  constructor(pieceDto, id) {
    this.#team = pieceDto["team"];
    this.#type = pieceDto["pieceLetter"];
    this.#x = pieceDto["x"];
    this.#y = pieceDto["y"];

    const defaultLocation = document.querySelector(".default-location");
    defaultLocation.insertAdjacentHTML("beforeend", this.makeComponent(id));
    this.#component = document.querySelector(`#p${id}`);
    this.#setPosition(this.#x, this.#y);
    this.#addEvent();
  }

  get x() {
    return this.#x;
  }

  get y() {
    return this.#y;
  }

  get team() {
    return this.#team;
  }

  get type() {
    return this.#type;
  }

  get component() {
    return this.#component;
  }

  makeComponent(id) {
    const imageName = this.#team[0].toLowerCase() + this.#type;
    return `<img class="piece" id="p${id}" src="${SRC_PATH}${imageName}.png">`
  }

  #setPosition(x, y) {
    this.#component.style.left = `${100 * x - 100}%`;
    this.#component.style.bottom = `${100 * y - 100}%`;
  }

  move(targetX, targetY) {
    const dx = targetX - this.#x;
    const dy = targetY - this.#y;
    const weight = Math.sqrt(dx * dx + dy * dy);
    this.#component.style.transition = `${SPEED * weight}s`
    this.#x = targetX;
    this.#y = targetY;
    this.#setPosition(targetX, targetY);
  }

  #addEvent() {
    this.#component.addEventListener("dragstart",
        e => this.#dragStart(e, this));
    this.#component.addEventListener("dragover", this.#allowDrop);
    this.#component.addEventListener("transitionend",
        e => this.#endTransition(e, this));
  }

  #dragStart(e, piece) {
    e.dataTransfer.setData("sourcePosition", `${this.#x}${this.#y}`);
    piece.highlight();
  }

  #allowDrop(e) {
    e.preventDefault()
  }

  #endTransition(e, piece) {
    piece.unhighlight();
  }

  highlight() {
    this.#component.src =
        `${SRC_PATH}${this.#team[0].toLowerCase()}${this.#type}s.png`;
  }

  unhighlight() {
    this.#component.src =
        `${SRC_PATH}${this.#team[0].toLowerCase()}${this.#type}.png`;
  }

  remove() {
    const parent = this.component.parentNode;
    parent.removeChild(this.#component);
  }
}

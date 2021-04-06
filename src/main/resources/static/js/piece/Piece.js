const TEAM_INDEX = 0;
const TYPE_INDEX = 1;
const X_INDEX = 2;
const Y_INDEX = 3;
const SPEED = 0.5;
const SRC_PATH = "./img/";

export class Piece {
  #x
  #y
  #team
  #type
  #component

  constructor(component) {
    this.#component = component;
    this.#x = this.#initialX();
    this.#y = this.#initialY();
    const id = this.#component.id;
    this.#team = id.charAt(TEAM_INDEX);
    this.#type = id.charAt(TYPE_INDEX);
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

  #initialX() {
    const id = this.#component.id;
    return parseInt(id.charAt(X_INDEX));
  }

  #initialY() {
    const id = this.#component.id;
    return parseInt(id.charAt(Y_INDEX));
  }

  move(targetX, targetY) {
    const dx = targetX - this.#x;
    const dy = targetY - this.#y;
    const relativeX = targetX - this.#initialX();
    const relativeY = targetY - this.#initialY()
    const weight = Math.sqrt(dx * dx + dy * dy);
    this.#component.style.transition = `${SPEED * weight}s`
    this.#component.style.transform =
        `translate(${100 * relativeX}%,${-100 * relativeY}%)`;
    this.#x = targetX;
    this.#y = targetY;
  }

  #addEvent() {
    this.#component.addEventListener("dragstart",
        e => this.#dragStart(e, this));
    this.#component.addEventListener("dragover", this.#allowDrop);
    this.#component.addEventListener("transitionend",
        e => this.#endTransition(e, this));
  }

  #dragStart(e, piece) {
    e.dataTransfer.setData("pieceId", e.target.id);
    piece.highlight();
  }

  #allowDrop(e) {
    e.preventDefault()
  }

  #endTransition(e, piece) {
    piece.unhighlight();
  }

  highlight() {
    this.#component.src = `${SRC_PATH}${this.#team}${this.#type}s.png`;
  }

  unhighlight() {
    this.#component.src = `${SRC_PATH}${this.#team}${this.#type}.png`;
  }

  remove() {
    const parent = this.component.parentNode;
    parent.removeChild(this.#component);
  }
}

import {Color} from "../color/Color.js"

export class Tile {
  #x
  #y
  #component

  constructor(component) {
    this.#convert(component.id);
    this.#component = component;
  }

  get x() {
    return this.#x;
  }

  get y() {
    return this.#y;
  }

  get component() {
    return this.#component;
  }

  #convert(id) {
    this.#x = id.charCodeAt(0) - "a".charCodeAt(0) + 1;
    this.#y = parseInt(id.charAt(1));
  }

  highlight(isMovable) {
    if (isMovable) {
      this.#component.style.backgroundColor = Color.GREEN;
    } else {
      this.#component.style.backgroundColor = Color.RED;
    }
  }

  unhighlight() {
    const positionSum = this.#x + this.#y;
    if (positionSum % 2 === 1) {
      this.#component.style.backgroundColor = Color.WHITE;
    } else {
      this.#component.style.backgroundColor = Color.BLACK;
    }
  }

  same(other) {
    return Object.is(this, other);
  }
}

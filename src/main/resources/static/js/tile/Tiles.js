import {Tile} from "./Tile.js"

export class Tiles {
  #tiles = []
  #components

  constructor() {
    this.#components = document.querySelectorAll(".tile");
    this.#components.forEach(component => this.#tiles.push(new Tile(component)))
  }

  get tiles() {
    return this.#tiles;
  }

  get components() {
    return this.#components;
  }

  findByComponent(component) {
    for (const tile of this.#tiles) {
      if (Object.is(tile.component, component)) {
        return tile;
      }
    }
  }

  findByPosition(x, y) {
    for (const tile of this.#tiles) {
      if (tile.x === x && tile.y === y) {
        return tile;
      }
    }
  }
}

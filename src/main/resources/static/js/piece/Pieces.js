import {Piece} from "./Piece.js";

export class Pieces {
  #pieces = []
  #components

  constructor() {
    this.#components = document.querySelectorAll(".piece");
    this.#components.forEach(
        component => this.#pieces.push(new Piece(component)))
  }

  move(piece, tile) {
    this.#removeIfExistent(tile);
    piece.move(tile.x, tile.y);
  }

  #removeIfExistent(tile) {
    const targetPiece = this.#findByPosition(tile.x, tile.y);
    if (targetPiece) {
      const index = this.#pieces.indexOf(targetPiece);
      this.#pieces = this.#pieces.slice(0, index).concat(
          this.#pieces.slice(index + 1));
      targetPiece.remove();
    }
  }

  #findByPosition(x, y) {
    for (const piece of this.#pieces) {
      if (piece.x === x && piece.y === y) {
        return piece;
      }
    }
  }

  findById(pieceId) {
    for (const piece of this.#pieces) {
      if (Object.is(piece.component.id, pieceId)) {
        return piece;
      }
    }
  }

  findByComponent(component) {
    for (const piece of this.#pieces) {
      if (Object.is(piece.component, component)) {
        return piece;
      }
    }
  }

  get pieces() {
    return this.#pieces;
  }

  get components() {
    return this.#components;
  }
}

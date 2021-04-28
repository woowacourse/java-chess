import {Piece} from "./Piece.js";

export class Pieces {
  #pieces = []

  constructor(pieceDtos) {
    for (let i = 0; i < pieceDtos.length; i++) {
      this.#pieces.push(new Piece(pieceDtos[i], i));
    }
  }

  get pieces() {
    return this.#pieces;
  }

  move(piece, tile) {
    this.#removeIfExistent(tile);
    piece.move(tile.x, tile.y);
  }

  #removeIfExistent(tile) {
    const targetPiece = this.findByPosition(tile.x, tile.y);
    if (targetPiece) {
      const index = this.#pieces.indexOf(targetPiece);
      this.#pieces = this.#pieces.slice(0, index).concat(
          this.#pieces.slice(index + 1));
      targetPiece.remove();
    }
  }

  findByPosition(x, y) {
    for (const piece of this.#pieces) {
      if (piece.x === x && piece.y === y) {
        return piece;
      }
    }
  }

  findBySourcePosition(sourcePosition) {
    const x = parseInt(sourcePosition[0]);
    const y = parseInt(sourcePosition[1]);
    return this.findByPosition(x, y);
  }

  findByComponent(component) {
    for (const piece of this.#pieces) {
      if (Object.is(piece.component, component)) {
        return piece;
      }
    }
  }
}

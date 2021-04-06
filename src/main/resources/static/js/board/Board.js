import {Tiles} from "../tile/Tiles.js"
import {Pieces} from "../piece/Pieces.js"

export class Board {
  #tiles
  #pieces
  #component
  #sourceTile

  constructor() {
    this.#tiles = new Tiles();
    this.#pieces = new Pieces();
    this.#component = document.querySelector(".grid");
    this.#sourceTile = null;
    this.#addEvent()
  }

  get tiles() {
    return this.#tiles;
  }

  get pieces() {
    return this.#pieces;
  }

  get component() {
    return this.#component;
  }

  get sourceTile() {
    return this.#sourceTile;
  }

  #addEvent() {
    this.#component.addEventListener("dragover", this.#allowDrop);
    this.#component.addEventListener("dragstart",
        e => this.#markSourceTile(e, this));
    this.#component.addEventListener("dragEnd",
        e => this.#unmarkSourceTile(e, this));
    this.#component.addEventListener("dragenter",
        e => this.#enterPiece(e, this));
    this.#component.addEventListener("dragleave",
        e => this.#leavePiece(e, this));
    this.#component.addEventListener("drop", e => this.#dropPiece(e, this));
  }

  #allowDrop(e) {
    e.preventDefault()
  }

  #markSourceTile(e, board) {
    board.#sourceTile = board.#findTileByPieceComponent(e.target);
  }

  #findTileByPieceComponent(component) {
    const piece = this.#pieces.findByComponent(component);
    return this.#tiles.findByPosition(piece.x, piece.y);
  }

  #unmarkSourceTile(e, board) {
    board.#sourceTile = null;
  }

  #enterPiece(e, board) {

  }

  #getTargetTile(target, board) {
    if (target.classList.contains("tile")) {
      return board.#tiles.findByComponent(target);
    }
    if (target.classList.contains("piece")) {
      return board.#findTileByPieceComponent(target);
    }
  }

  #leavePiece(e, board) {
    board.#unhighlight(e.target)
  }

  #dropPiece(e, board) {

  }

  #unhighlight(target) {
    if (target.classList.contains("tile")) {
      const tile = this.#tiles.findByComponent(target);
      tile.unhighlight();
    }
    if (target.classList.contains("piece")) {
      const tile = this.#findTileByPieceComponent(target);
      tile.unhighlight();
    }
  }
}

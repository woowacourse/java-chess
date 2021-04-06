import {Tiles} from "../tile/Tiles.js"
import {Pieces} from "../piece/Pieces.js"
import {getData, postData} from "../utils/FetchUtil.js"

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

  async #enterPiece(e, board) {
    if (!e.target.classList.contains("tile") &&
        !e.target.classList.contains("piece")) {
      return;
    }
    let targetTile = this.#getTargetTile(e.target, board);
    if (board.#sourceTile.same(targetTile)) {
      return;
    }
    const params = {
      source: board.#sourceTile.component.id,
      target: targetTile.component.id
    }
    const response = await getData("http://localhost:4567/game", params);
    targetTile.highlight(response["isMovable"]);
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

  async #dropPiece(e, board) {
    const pieceId = e.dataTransfer.getData("pieceId");
    const piece = board.#pieces.findById(pieceId);
    const sourceTile = board.#tiles.findByPosition(piece.x, piece.y);
    const targetTile = this.#getTargetTile(e.target, board);
    if (sourceTile.same(targetTile)) {
      piece.unhighlight();
      return;
    }

    const params = {
      source: sourceTile.component.id,
      target: targetTile.component.id
    }
    const response = await getData("http://localhost:4567/game", params);
    const isMovable = response["isMovable"]
    if (isMovable) {
      await this.#requestMove(piece, targetTile, params);
    } else {
      piece.unhighlight();
    }
    targetTile.unhighlight();
  }

  async #requestMove(piece, targetTile, body) {
    const response = await postData("http://localhost:4567/game", body);
    const isSuccessful = response["isSuccessful"];
    if (isSuccessful) {
      this.#pieces.move(piece, targetTile)
    } else {
      console.error(response["message"]);
    }
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

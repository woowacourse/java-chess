import {Tiles} from "../tile/Tiles.js"
import {Pieces} from "../piece/Pieces.js"
import {getData, postData} from "../utils/FetchUtil.js"

const url = "http://localhost:4567/api";

export class Board {
  #tiles
  #pieces
  #component
  #sourceTile

  constructor(pieceDtos) {
    this.#tiles = new Tiles();
    this.#pieces = new Pieces(pieceDtos);
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
    const targetTile = this.#getTargetTile(e.target, board);
    const sourceTile = board.#sourceTile;
    if (sourceTile.same(targetTile)) {
      return;
    }
    const params = {
      source: board.#sourceTile.component.id,
      target: targetTile.component.id,
      team: this.#pieces.findByPosition(sourceTile.x, sourceTile.y).team
    }

    const gameId = this.#findGameIdInUri();
    try {
      const response = await getData(
          `${url}/game/${gameId}/piece`, params
      );
      targetTile.highlight(response["isMovable"]);
    } catch (e) {
      console.log(e);
    }
  }

  #findGameIdInUri() {
    const path = window.location.pathname
    const gameId = path.substr(path.lastIndexOf("/") + 1);
    return gameId;
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
    const copy = e.target;
    board.#unhighlight(copy);
  }

  async #dropPiece(e, board) {
    const sourcePosition = e.dataTransfer.getData("sourcePosition");
    const piece = board.#pieces.findBySourcePosition(sourcePosition);
    const sourceTile = board.#tiles.findByPosition(piece.x, piece.y);
    const targetTile = this.#getTargetTile(e.target, board);
    if (sourceTile.same(targetTile)) {
      piece.unhighlight();
      return;
    }

    const params = {
      source: sourceTile.component.id,
      target: targetTile.component.id,
      team: piece.team
    }
    const gameId = this.#findGameIdInUri();
    const response = await getData(
        `${url}/game/${gameId}/piece`, params);
    const isMovable = response["isMovable"]
    if (isMovable) {
      await this.#requestMove(piece, targetTile, params, gameId);
    } else {
      piece.unhighlight();
    }
    targetTile.unhighlight();
  }

  async #requestMove(piece, targetTile, body, gameId) {
    const response = await postData(
        `${url}/game/${gameId}/piece`, body);
    this.#pieces.move(piece, targetTile)
    const isFinished = response["isFinished"]
    if (isFinished) {
      const winner = response["winner"];
      const back = confirm(`${winner}가 이겼습니다. 확인을 누르면 홈으로 돌아갑니다.`)
      if (back) {
        window.location.href = "/";
      }
    }
  }

  #unhighlight(target) {
    if (target.classList.contains("tile")) {
      const tile = this.#tiles.findById(target.id);
      window.setTimeout(function () {
        tile.unhighlight.call(tile);
      }, 50);
    }
    if (target.classList.contains("piece")) {
      const tile = this.#findTileByPieceComponent(target);
      window.setTimeout(function () {
        tile.unhighlight.call(tile);
      }, 50);
    }
  }

  findPieceBySourcePosition(sourcePosition) {
    return this.#pieces.findBySourcePosition(sourcePosition);
  }
}

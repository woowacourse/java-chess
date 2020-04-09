export const PIECES = {
  BLACK_KING: `<img src="../image/pieces/black_king.png" class="black king piece"/>`,
  BLACK_QUEEN: `<img src="../image/pieces/black_queen.png" class="black queen piece"/>`,
  BLACK_ROOK: `<img src="../image/pieces/black_rook.png" class="black rook piece"/>`,
  BLACK_BISHOP: `<img src="../image/pieces/black_bishop.png" class="black bishop piece"/>`,
  BLACK_KNIGHT: `<img src="../image/pieces/black_knight.png" class="black knight piece"/>`,
  BLACK_PAWN: `<img src="../image/pieces/black_pawn.png" class="black piece pawn"/>`,
  WHITE_KING: `<img src="../image/pieces/white_king.png" class="white king piece"/>`,
  WHITE_QUEEN: `<img src="../image/pieces/white_queen.png" class="white queen piece"/>`,
  WHITE_ROOK: `<img src="../image/pieces/white_rook.png" class="white rook piece"/>`,
  WHITE_BISHOP: `<img src="../image/pieces/white_bishop.png" class="white bishop piece"/>`,
  WHITE_KNIGHT: `<img src="../image/pieces/white_knight.png" class="white knight piece"/>`,
  WHITE_PAWN: `<img src="../image/pieces/white_pawn.png" class="white pawn piece"/>`,
};

export const AVAILABLE_PATH = `<div class="available-path"/>`

/**
 * @return {string}
 */
export function ROOM_TEMPLATE(room, index, whiteScore, blackScore) {
  const whiteRecord = room["백"]["record"]["record"];
  const blackRecord = room["흑"]["record"]["record"];
  return `
    <div class="room room-${index}">
      <div class="room-number">${index}</div>
      <div class="players">
        <div class="player">${room["백"]["username"]}
          <div class="player-record">(${whiteRecord["WIN"]}승 ${whiteRecord["DRAW"]}무 ${whiteRecord["LOSE"]}패)</div>
        </div>
        <div class="player">${room["흑"]["username"]}
          <div class="player-record">(${blackRecord["WIN"]}승 ${blackRecord["DRAW"]}무 ${blackRecord["LOSE"]}패)</div>
        </div>
      </div>
      <div class="room-status room-score">${whiteScore || 38} : ${blackScore || 38}</div>
    </div>`
}

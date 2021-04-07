export const PIECES = {
  BLACK_KING: `<img src= "/image/K.png" class = "black piece"/>`,
  BLACK_QUEEN: `<img src= "/image/Q.png" class = "black piece" />`,
  BLACK_BISHOP: `<img src= "/image/B.png" class = "black piece"/>`,
  BLACK_KNIGHT: `<img src= "/image/N.png" class = "black piece"/>`,
  BLACK_ROOK: `<img src= "/image/R.png" class = "black piece"/>`,
  BLACK_PAWN: `<img src= "/image/P.png" class = "black piece"/>`,
  WHITE_KING: `<img src= "/image/w_K.png" class = "white piece"/>`,
  WHITE_QUEEN: `<img src= "/image/w_Q.png" class = "white piece"/>`,
  WHITE_BISHOP: `<img src= "/image/w_B.png" class = "white piece"/>`,
  WHITE_KNIGHT: `<img src= "/image/w_N.png" class = "white piece"/>`,
  WHITE_ROOK: `<img src= "/image/w_R.png" class = "white piece"/>`,
  WHITE_PAWN: `<img src= "/image/w_P.png" class = "white piece"/>`,
  '.': `<img/>`,
}

export function SCORE_TEMPLATE(whiteScore, blackScore) {
  return `
  <div class="white">
          <div class="player-color">W</div>
          <div class="player">(PL1)</div>
          <div class="player-score">${whiteScore || 38}</div>
        </div>
        <div class="vertical-divider"></div>
        <div class="black">
          <div class="player-color">B</div>
          <div class="player">(PL2)</div>
          <div class="player-score">${blackScore || 38}</div>
        </div>`;
}
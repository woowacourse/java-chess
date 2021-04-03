export const PIECES = {
  BLACK_KING: `<img src= "/image/K.png" class = "piece"/>`,
  BLACK_QUEEN: `<img src= "/image/Q.png" class = "piece" />`,
  BLACK_BISHOP: `<img src= "/image/B.png" class = "piece"/>`,
  BLACK_KNIGHT: `<img src= "/image/N.png" class = "piece"/>`,
  BLACK_ROOK: `<img src= "/image/R.png" class = "piece"/>`,
  BLACK_PAWN: `<img src= "/image/P.png" class = "piece"/>`,
  WHITE_KING: `<img src= "/image/w_K.png" class = "piece"/>`,
  WHITE_QUEEN: `<img src= "/image/w_Q.png" class = "piece"/>`,
  WHITE_BISHOP: `<img src= "/image/w_B.png" class = "piece"/>`,
  WHITE_KNIGHT: `<img src= "/image/w_N.png" class = "piece"/>`,
  WHITE_ROOK: `<img src= "/image/w_R.png" class = "piece"/>`,
  WHITE_PAWN: `<img src= "/image/w_P.png" class = "piece"/>`,
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
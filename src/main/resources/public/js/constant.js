export const PIECES = {
  BLACK_KING: `<img src= "/image/K.png"/>`,
  BLACK_QUEEN: `<img src= "/image/Q.png"/>`,
  BLACK_BISHOP: `<img src= "/image/B.png"/>`,
  BLACK_KNIGHT: `<img src= "/image/N.png"/>`,
  BLACK_ROOK: `<img src= "/image/R.png"/>`,
  BLACK_PAWN: `<img src= "/image/P.png"/>`,
  WHITE_KING: `<img src= "/image/w_K.png"/>`,
  WHITE_QUEEN: `<img src= "/image/w_Q.png"/>`,
  WHITE_BISHOP: `<img src= "/image/w_B.png"/>`,
  WHITE_KNIGHT: `<img src= "/image/w_N.png"/>`,
  WHITE_ROOK: `<img src= "/image/w_R.png"/>`,
  WHITE_PAWN: `<img src= "/image/w_P.png"/>`,
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
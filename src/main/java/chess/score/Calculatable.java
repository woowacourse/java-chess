package chess.score;

import chess.game.ChessSet;

public interface Calculatable {
    Score calculate(ChessSet chessSet);
}

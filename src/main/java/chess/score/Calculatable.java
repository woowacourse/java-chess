package chess.score;

import chess.board.ChessBoard;
import chess.game.ChessSet;
import chess.player.Player;

public interface Calculatable {
    Score calculate(ChessSet chessSet);
}

package chess.score;

import chess.board.ChessBoard;
import chess.player.Player;

public interface Calculatable {
    Score calculate(ChessBoard chessBoard, Player player);
}

package chess.score;

import chess.board.ChessBoard;
import chess.player.Player;
import chess.team.Team;

public interface Calculatable {
    Score calculate(ChessBoard chessBoard, Player player);
}

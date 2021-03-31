package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.order.MoveResult;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

public interface ChessGameManager {
    ChessGameManager start();

    ChessGameManager end();

    MoveResult move(Position from, Position to);

    Board getBoard();

    boolean isNotEnd();

    boolean isStart();

    boolean isKingDead();

    ChessGameStatistics getStatistics();
}

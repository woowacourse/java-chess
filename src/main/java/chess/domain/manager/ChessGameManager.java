package chess.domain.manager;

import chess.domain.board.Board;
import chess.domain.order.MoveResult;
import chess.domain.piece.attribute.Color;
import chess.domain.position.Position;
import chess.domain.statistics.ChessGameStatistics;

public interface ChessGameManager {
    ChessGameManager start();

    ChessGameManager end();

    MoveResult move(Position from, Position to);

    Board getBoard();

    Color nextColor();

    boolean isNotEnd();

    boolean isEnd();

    boolean isStart();

    boolean isKingDead();

    long getId();

    ChessGameStatistics getStatistics();
}

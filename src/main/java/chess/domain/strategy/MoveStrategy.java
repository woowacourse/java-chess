package chess.domain.strategy;

import chess.domain.ChessBoard;
import chess.domain.position.Position;

public interface MoveStrategy {
    void isMovable(Position source, Position target, ChessBoard chessBoard);
}

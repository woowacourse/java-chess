package chess.domain.piece;

import chess.domain.board.Position;

@FunctionalInterface
public interface PositionChecker {
    Piece getPiece(Position position);
}

package chess.domain.board;

import chess.domain.piece.piecefigure.Piece;

@FunctionalInterface
public interface PositionChecker {
    Piece getPiece(Position position);
}

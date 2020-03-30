package chess.piece.movestrategy;

import chess.coordinate.Vector;
import chess.piece.Piece;

public interface PawnMoveStrategy {
    boolean support(Vector vector);

    boolean movable(Vector vector, Piece targetPiece);
}

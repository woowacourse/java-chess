package chess.model.piece;

import chess.model.position.ChessPosition;

import java.util.List;

public interface MoveStrategy {
    List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece);
}

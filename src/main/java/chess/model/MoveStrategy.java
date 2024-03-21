package chess.model;

import java.util.List;

public interface MoveStrategy {
    List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece);
}

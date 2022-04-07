package chess.domain.board.strategy;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

@FunctionalInterface
public interface CreateBoardStrategy {
    Map<Position, Piece> createPieces();
}

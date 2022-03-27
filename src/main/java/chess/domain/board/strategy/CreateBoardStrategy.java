package chess.domain.board.strategy;

import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

@FunctionalInterface
public interface CreateBoardStrategy {
    Map<Position, Piece> createPieces();
}

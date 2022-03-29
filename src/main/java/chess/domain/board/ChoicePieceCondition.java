package chess.domain.board;

import chess.domain.piece.Piece;

@FunctionalInterface
public interface ChoicePieceCondition {
    boolean test(Piece piece);
}

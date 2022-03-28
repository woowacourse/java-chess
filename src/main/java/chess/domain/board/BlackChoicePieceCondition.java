package chess.domain.board;

import chess.domain.piece.Piece;

public class BlackChoicePieceCondition implements ChoicePieceCondition {
    @Override
    public boolean test(Piece piece) {
        return piece.isBlack();
    }
}

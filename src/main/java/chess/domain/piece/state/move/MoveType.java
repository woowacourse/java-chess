package chess.domain.piece.state.move;

import chess.domain.piece.Piece;

public enum MoveType {
    INITIALIZED,
    MOVED,
    ATTACKED_SUBORDINATE,
    ATTACKED_KING,
    STAYED;

    public MoveType update(Piece from, Piece to) {
        if (from.equals(to)) {
            return STAYED;
        }
        if (to.isBlank()) {
            return MOVED;
        }

        if (from.isEnemy(to) && to.isKing()) {
            return ATTACKED_KING;
        }

        return ATTACKED_SUBORDINATE;
    }
}

package chess.domain.piece.state.move;

import chess.domain.piece.Piece;

public enum  MoveType {
    INITIALIZED,
    MOVED,
    ATTACKED_SUBORDINATE,
    ATTACKED_KING;

    //todo: 질문: 여기서 from과 to가 같을 것도 고려해야하나?
    public MoveType update(Piece from, Piece to) {
        if (to.isBlank()) {
            return MOVED;
        }

        if (from.isEnemy(to) && to.isKing()) {
            return ATTACKED_KING;
        }

        return ATTACKED_SUBORDINATE;
    }
}

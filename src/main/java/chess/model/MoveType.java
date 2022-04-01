package chess.model;

import chess.model.piece.Piece;

public enum MoveType {
    MOVE,
    ATTACK;

    public static MoveType of(Piece source, Piece target) {
        if (source.isSameTeam(target)) {
            throw new IllegalArgumentException("타겟 위치에 같은 팀 말이 있습니다.");
        }
        if (source.isEnemy(target)) {
            return ATTACK;
        }
        return MOVE;
    }
}

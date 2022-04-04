package chess.domain.piece;

import chess.domain.move.MoveStrategy;

public final class Blank extends Piece {

    private static final double POINT = 0;

    public Blank() {
        super("BLANK", Team.NONE, POINT);
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        throw new IllegalStateException("[ERROR] 움직이고자 하는 기물이 존재하지 않습니다.");
    }
}

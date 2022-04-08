package chess.domain.piece;

import chess.domain.move.MoveStrategy;

public final class Blank extends Piece {

    private static final double POINT = 0;
    private static final String NAME = " ";

    public Blank() {
        super(Team.NONE, NAME ,POINT);
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        throw new IllegalStateException("[ERROR] 이동전략을 가져올 수 없는 기물입니다.");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}

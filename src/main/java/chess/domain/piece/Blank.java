package chess.domain.piece;

import chess.domain.move.MoveStrategy;

public class Blank extends Piece {

    public Blank() {
        super(Color.NONE);
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
}

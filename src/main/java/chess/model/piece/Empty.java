package chess.model.piece;

import chess.model.position.Movement;

public class Empty extends Piece {
    private static final Piece EMPTY = new Empty();

    private Empty() {
        super(Color.NONE);
    }

    public static Piece getInstance() {
        return EMPTY;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        throw new IllegalArgumentException("기물이 없을 때는 움직일 수 없습니다.");
    }
}

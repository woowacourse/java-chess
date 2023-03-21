package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import java.util.List;

public class Empty implements Piece {

    private final Type type;
    private final Side side;

    public Empty() {
        this.type = Type.EMPTY;
        this.side = Side.NEUTRALITY;
    }

    @Override
    public List<Position> findMovablePosition(final Position source, final Board board) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public String name() {
        return type.getSymbol(side);
    }

    @Override
    public Side side() {
        return side;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public void changePawnMoveState() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}

package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public class None extends Piece {
    private static final String ERROR_CANT_MOVE = "말이 없어 이동할 수 없습니다.";

    public None() {
        super(Camp.NONE);
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        throw new IllegalArgumentException(ERROR_CANT_MOVE);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        this.move(beforePosition, afterPosition, moveFunction);
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        return false;
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public boolean isBishop() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isQueen() {
        return false;
    }

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }
}

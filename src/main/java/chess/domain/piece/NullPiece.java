package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class NullPiece extends Piece {

    private static final String CANT_MOVE_EMPTY_PIECE = "빈 기물을 움직일 수 없습니다.";
    private static final int SCORE = 0;

    public NullPiece(final Camp camp) {
        super(camp);
    }

    @Override
    public void move(final Position beforePosition, final Position afterPosition, final Consumer<Piece> moveFunction) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public void capture(final Position beforePosition,
                        final Position afterPosition,
                        final Consumer<Piece> moveFunction) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    protected boolean canMove(final Position beforePosition, final Position afterPosition) {
        throw new IllegalStateException(CANT_MOVE_EMPTY_PIECE);
    }

    @Override
    public boolean isNull() {
        return true;
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
    public double getScore() {
        return SCORE;
    }
}

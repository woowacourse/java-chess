package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.Position;
import java.util.function.Consumer;

public final class NullPiece extends Piece {

    private static final String INVALID_BLANK_MOVE_EXCEPTION = "빈칸은 움직일 수 없습니다.";

    public NullPiece() {
        super(Color.NONE, Type.NONE);
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public Color getColor() {
        return super.getColor();
    }

    @Override
    public void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        throw new UnsupportedOperationException(INVALID_BLANK_MOVE_EXCEPTION);
    }

    @Override
    public void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction) {
        throw new UnsupportedOperationException(INVALID_BLANK_MOVE_EXCEPTION);
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public String getSymbol() {
        return ".";
    }

    @Override
    protected boolean canMove(Position beforePosition, Position afterPosition) {
        return false;
    }
}

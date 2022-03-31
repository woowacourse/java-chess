package chess.domain.piece;

import java.util.function.Consumer;

import chess.domain.Color;
import chess.domain.board.Position;

public abstract class Piece {
    protected static final String INVALID_TARGET_POSITION_EXCEPTION = "이동할 수 없는 위치입니다.";

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public final boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public final boolean isSameColorWith(Piece targetPiece) {
        return this.color == targetPiece.color;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public abstract void move(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction);

    public abstract void capture(Position beforePosition, Position afterPosition, Consumer<Piece> moveFunction);

    protected abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract double getScore();

    public abstract String getSymbol();
}

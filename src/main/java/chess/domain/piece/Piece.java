package chess.domain.piece;

import chess.domain.piece.exception.IllegalPieceMoveException;
import chess.domain.piece.state.MoveState;

public class Piece {

    private static final Piece empty = PieceFactory.getInstance(PieceType.EMPTY, Color.NONE);

    private final Color color;
    private final MoveState moveState;

    Piece(Color color, MoveState moveState) {
        this.color = color;
        this.moveState = moveState;
    }

    public static Piece empty() {
        return empty;
    }

    public Piece move(int x, int y, Piece piece) {
        boolean canMove = moveState.canMove(x, color.colorForwardDirection(y), piece.compareColor(color));
        if (!canMove) {
            throw new IllegalPieceMoveException();
        }
        return new Piece(color, moveState.getNextState());
    }

    private ColorCompareResult compareColor(Color color) {
        return ColorCompareResult.of(this.color, color);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNotSameColor(Color color) {
        return !isSameColor(color);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", moveState=" + moveState +
                '}';
    }

    public PieceType getType() {
        return moveState.getType();
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return moveState.getScore();
    }

    public boolean isKing() {
        return moveState.isKing();
    }
}

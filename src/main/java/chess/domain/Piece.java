package chess.domain;

import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.state.MoveState;

public class Piece {

    private static final Piece empty = new Piece(PieceType.EMPTY, Color.NONE);

    private final Color color;
    private MoveState moveState;

    public Piece(PieceType pieceType, Color color) {
        this.color = color;
        moveState = pieceType.getState();
    }

    public static Piece empty() {
        return empty;
    }

    public void move(int x, int y, Piece piece) {
        if (!moveState.canMove(x, color.colorDirection(y), piece.compareColor(color))) {
            throw new IllegalPieceMoveException();
        }
        moveState = moveState.getNextState();
    }

    private ColorCompareResult compareColor(Color color) {
        return ColorCompareResult.of(this.color, color);
    }

    public PieceType getType() {
        return PieceType.getName(moveState);
    }

    public Color getColor() {
        return color;
    }
}

package chess.domain;

import chess.domain.state.MoveState;

public class Piece {
    private final PieceType type;
    private final Color color;
    private MoveState moveState;

    public Piece(PieceType pieceType, Color color) {
        type = pieceType;
        this.color = color;
        moveState = pieceType.getState();
    }

    public Piece(String type, Color color) {
        this(PieceType.from(type), color);
    }

    public Piece() {
        this(PieceType.EMPTY, Color.NONE);
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public void move(int x, int y, Piece piece) {
        moveState = moveState.move(x, color.colorDirection(y), piece.isSameColor(color));
    }

    private ColorCompareResult isSameColor(Color color) {
        return ColorCompareResult.of(this.color, color);
    }
}

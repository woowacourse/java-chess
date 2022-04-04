package chess.domain.piece;

import chess.domain.board.Point;

import java.util.Map;

public abstract class Piece {

    protected final Color color;
    private final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSameType(PieceType type) {
        return this.type == type;
    }

    public double getScore() {
        return this.type.getScore();
    }

    protected boolean isEmptyPoint(Map<Point, Piece> pointPieces, Point point) {
        Piece piece = pointPieces.get(point);
        return piece.isSameType(PieceType.EMPTY);
    }

    public abstract void move(Map<Point, Piece> pointPieces, Point from, Point to);

    public String convertPieceInfoToString() {
        return color.toString().toLowerCase() + "-" + type.toString().toLowerCase();
    }
}

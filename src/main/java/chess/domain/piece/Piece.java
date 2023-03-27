package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import static chess.direction.Direction.BOTTOM_LEFT;
import static chess.direction.Direction.BOTTOM_RIGHT;
import static chess.direction.Direction.TOP_LEFT;
import static chess.direction.Direction.TOP_RIGHT;

public abstract class Piece {

    private final String name;
    private final Color color;
    private double score;

    public Piece(String name, Color color, double score) {
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public String findName() {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

    public Direction findDirection(Position start, Position end) {
        return Direction.findDirectionByGap(start, end, this);
    }

    public abstract boolean isMovable(Position start, Position end, Color colorOfDestination);

    public abstract int calculateKing(int count);

    public abstract int calculatePawn(int count, Color color);

    private static boolean isEqualTo(Direction o1, Direction o2) {
        return (o1.getX() == o2.getX()) && (o1.getY() == o2.getY());
    }

    public static boolean isDiagonal(Direction direction) {
        return isEqualTo(direction, TOP_LEFT)
                || isEqualTo(direction, TOP_RIGHT)
                || isEqualTo(direction, BOTTOM_LEFT)
                || isEqualTo(direction, BOTTOM_RIGHT);
    }

    public abstract boolean findDirection(Direction direction, Position start, Position end, Piece piece);

    public boolean isSameColor(Color color) {
        return this.color.equals(color);
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }

    public boolean isPawn() {
        return name.equalsIgnoreCase("p");
    }

    public static Piece valueOf(String name) {
        if (name.equals("B")) {
            return new Bishop(PieceInfo.BLACK_BISHOP_INFO);
        }
        if (name.equals("b")) {
            return new Bishop(PieceInfo.WHITE_BISHOP_INFO);
        }

        if (name.equals("k")) {
            return new King(PieceInfo.WHITE_KING_INFO);
        }
        if (name.equals("K")) {
            return new King(PieceInfo.BLACK_KING_INFO);
        }

        if (name.equals("q")) {
            return new Queen(PieceInfo.WHITE_QUEEN_INFO);
        }
        if (name.equals("Q")) {
            return new Queen(PieceInfo.BLACK_QUEEN_INFO);
        }

        if (name.equals("p")) {
            return new Pawn(PieceInfo.WHITE_PAWN_INFO);
        }
        if (name.equals("P")) {
            return new Pawn(PieceInfo.BLACK_QUEEN_INFO);
        }

        if (name.equals("n")) {
            return new Knight(PieceInfo.WHITE_QUEEN_INFO);
        }

        return new Knight(PieceInfo.BLACK_QUEEN_INFO);
    }

}

package chess.model.piece;

import chess.model.ChessBoard;
import chess.model.element.Color;
import chess.model.position.Position;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean canMove(Position src, Position dest, ChessBoard board) {
        double distanceByPositions = calculateDistanceBy(src, dest);

        if (!isNotWithInDirection(src, dest)) {
            return false;
        }
        if (!isWithInRangeByMovement(distanceByPositions)) {
            return false;
        }
        if (!passFilter(src, dest, board)) {
            return false;
        }
        return true;
    }

    protected abstract boolean isNotWithInDirection(Position src, Position dest);

    private double calculateDistanceBy(Position src, Position dest) {
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);

        int xx = calculateSquare(srcNumber[0], destNumber[0]);
        int yy = calculateSquare(srcNumber[1], destNumber[1]);

        return Math.sqrt(xx + yy);
    }

    protected static int[] findNumberByPosition(Position position) {
        int columnNumber = position.column().ordinal();
        int rowNumber = position.row().ordinal();
        return new int[]{rowNumber, columnNumber};
    }

    private static int calculateSquare(int rowNumber, int destNumber) {
        return (int) Math.pow(calculateAbs(rowNumber, destNumber), 2);
    }

    protected static int calculateAbs(int rowNumber, int destNumber) {
        return Math.abs(rowNumber - destNumber);
    }

    protected abstract boolean isWithInRangeByMovement(double distance);


    protected abstract boolean passFilter(Position src, Position dest, ChessBoard board);

    protected boolean isVertical(Position src, Position dest) { // 수직
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);

        return srcNumber[0] == destNumber[0] && srcNumber[1] != destNumber[1];
    }

    protected boolean isHorizontal(Position src, Position dest) { // 수평
        int[] srcNumber = findNumberByPosition(src);
        int[] destNumber = findNumberByPosition(dest);

        return srcNumber[0] != destNumber[0] && srcNumber[1] == destNumber[1];
    }

    public Color getColor() {
        return color;
    }
}

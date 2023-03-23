package domain;

import java.util.HashMap;
import java.util.Map;

import domain.piece.Vector;

public class Square {

    private static final Map<String, Square> caches = new HashMap<>();
    private static final int CHESS_COLUMN_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int LENGTH_SIZE = 2;

    private final ChessColumn chessColumn;
    private final Rank rank;

    private Square(ChessColumn chessColumn, Rank rank) {
        this.chessColumn = chessColumn;
        this.rank = rank;
    }

    public static Square of(int column, int row) {
        validateSize(column, row);
        return of(ChessColumn.find(column), Rank.find(row));
    }

    private static void validateSize(int column, int row) {
        if (column > ChessColumn.MAX_SIZE || row > Rank.MAX_SIZE ||
                column < ChessColumn.MIN_SIZE || row < Rank.MIN_SIZE) {
            throw new IllegalArgumentException("체스판을 벗어날 수 없습니다.");
        }
    }

    public static Square of(ChessColumn chessColumn, Rank rank) {
        String key = chessColumn.name() + rank.name();
        return caches.computeIfAbsent(key, ignored -> new Square(chessColumn, rank));
    }

    public static Square of(String square) {
        validateLength(square);
        char column = square.charAt(CHESS_COLUMN_INDEX);
        char row = square.charAt(RANK_INDEX);
        ChessColumn chessColumn = ChessColumn.find(column);
        Rank rank = Rank.find(Character.getNumericValue(row));
        return Square.of(chessColumn, rank);
    }

    private static void validateLength(String square) {
        if (square.length() != LENGTH_SIZE) {
            throw new IllegalArgumentException("위치는 두자리로 이루어져있습니다.");
        }
    }

    public Square add(Vector direction) {
        int column = chessColumn.getColumn() + direction.getX();
        int row = rank.getRow() + direction.getY();
        validateSize(column, row);
        return of(column, row);
    }

    public Vector calculateVector(Square other) {
        int column = chessColumn.minus(other.chessColumn);
        int row = rank.minus(other.rank);
        return Vector.of(column, row);
    }

    @Override
    public String toString() {
        return "Square{" +
                "chessColumn=" + chessColumn +
                ", rank=" + rank +
                '}';
    }
}

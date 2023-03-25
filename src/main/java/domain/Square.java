package domain;

import domain.piece.Direction;
import java.util.HashMap;
import java.util.Map;

public class Square {

    private static final Map<String, Square> caches = new HashMap<>();

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

    public Square add(Direction direction) {
        int column = chessColumn.getColumn() + direction.getX();
        int row = rank.getRow() + direction.getY();
        validateSize(column, row);
        return of(column, row);
    }

    public Direction calculateVector(Square other) {
        int column = chessColumn.minus(other.chessColumn);
        int row = rank.minus(other.rank);
        return Direction.of(column, row);
    }
}

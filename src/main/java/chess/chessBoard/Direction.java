package chess.chessBoard;

import chess.chessBoard.position.File;
import chess.chessBoard.position.Position;
import chess.chessBoard.position.Rank;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(1, -1),
    SOUTHWEST(-1, -1),
    SOUTHEAST(-1, 1),

    KNIGHT_EAST_RIGHT(-1, 2),
    KNIGHT_EAST_LEFT(1, 2),
    KNIGHT_WEST_RIGHT(1, -2),
    KNIGHT_WEST_LEFT(-1, -2),
    KNIGHT_SOUTH_RIGHT(-2, -1),
    KNIGHT_SOUTH_LEFT(-2, 1),
    KNIGHT_NORTH_RIGHT(2, 1),
    KNIGHT_NORTH_LEFT(2, -1),

    BLACK_PAWN_FORWARD_TWO(-2, 0),
    WHITE_PAWN_FORWARD_TWO(2, 0),
    ;

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isMovablePosition(Rank rank, File file) {
        return rank.canAdd(row) && file.canAdd(column);
    }

    public Position createMovablePosition(Rank rank, File file) {
        return Position.of(rank.add(row), file.add(column));
    }
}

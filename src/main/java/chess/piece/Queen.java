package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Queen extends Piece {

    private static final Position WHITE_QUEEN_POSITION = new Position(Row.ONE, Column.D);
    private static final Position BLACK_QUEEN_POSITION = new Position(Row.EIGHT, Column.D);

    private static final List<Function<Position, Position>> MOVEMENTS = List.of(
            Position::moveUp,
            Position::moveDown,
            Position::moveLeft,
            Position::moveRight,
            Position::moveLeftUp,
            Position::moveLeftDown,
            Position::moveRightUp,
            Position::moveRightDown
    );

    public Queen(final Position position, final Color color) {
        super(position, color);
    }

    public static List<Piece> initialize() {
        List<Piece> queens = new ArrayList<>();
        queens.add(new Queen(WHITE_QUEEN_POSITION, Color.WHITE));
        queens.add(new Queen(BLACK_QUEEN_POSITION, Color.BLACK));
        return queens;
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        final List<Position> positions = new ArrayList<>();
        for (Function<Position, Position> movement : MOVEMENTS) {
            addAvailablePositions(board, getPosition(), movement, positions);
        }
        return positions;
    }

    private void addAvailablePositions(final Board board, final Position position, final Function<Position, Position> function, final List<Position> positions) {
        Position movedPosition;
        try {
            movedPosition = function.apply(position);
        } catch (IllegalStateException e) {
            return;
        }
        if (!isAvailablePosition(board, movedPosition)) {
            return;
        }
        if (!board.isEmptyPosition(movedPosition)) {
            if (board.isEnemy(movedPosition, this)) {
                positions.add(movedPosition);
            }
            return;
        }
        positions.add(movedPosition);
        addAvailablePositions(board, movedPosition, function, positions);
    }

    @Override
    public Piece copyOf(final Position position) {
        return new Queen(position, getColor());
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}

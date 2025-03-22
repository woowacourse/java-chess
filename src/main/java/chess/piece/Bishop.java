package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Bishop extends Piece {

    private static final List<Position> WHITE_BISHOP_POSITIONS = List.of(
            new Position(Row.ONE, Column.C), new Position(Row.ONE, Column.F)
    );

    private static final List<Position> BLACK_BISHOP_POSITIONS = List.of(
            new Position(Row.EIGHT, Column.C), new Position(Row.EIGHT, Column.F)
    );

    private static final List<Function<Position, Position>> MOVEMENTS = List.of(
            Position::moveLeftUp,
            Position::moveLeftDown,
            Position::moveRightUp,
            Position::moveRightDown
    );

    public Bishop(final Position position, final Color color) {
        super(position, color);
    }

    public static List<Piece> initialize() {
        final List<Piece> pieces = new ArrayList<>();
        final List<Bishop> whiteBishops = WHITE_BISHOP_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.WHITE))
                .toList();
        final List<Bishop> blackBishops = BLACK_BISHOP_POSITIONS.stream()
                .map(position -> new Bishop(position, Color.BLACK))
                .toList();
        pieces.addAll(whiteBishops);
        pieces.addAll(blackBishops);
        return pieces;
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        final List<Position> positions = new ArrayList<>();
        for (Function<Position, Position> movement : MOVEMENTS) {
            addAvailablePositions(board, getPosition(), movement, positions);
        }
        return positions;
    }

    @Override
    public Piece copyOf(final Position position) {
        return new Bishop(position, getColor());
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
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
}

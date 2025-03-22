package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Rook extends Piece {

    private static final List<Position> WHITE_ROOK_POSITIONS = List.of(
            new Position(Row.ONE, Column.A), new Position(Row.ONE, Column.H)
    );

    private static final List<Position> BLACK_ROOK_POSITIONS = List.of(
            new Position(Row.EIGHT, Column.A), new Position(Row.EIGHT, Column.H)
    );

    private static final List<Function<Position, Position>> MOVEMENTS = List.of(
            Position::moveUp,
            Position::moveDown,
            Position::moveLeft,
            Position::moveRight
    );

    public Rook(final Position position, final Color color) {
        super(position, color);
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        final List<Position> positions = new ArrayList<>();
        for (Function<Position, Position> movement : MOVEMENTS) {
            addAvailablePositions(board, getPosition(), movement, positions);
        }
        return positions;
    }

    public static List<Piece> initialize() {
        final List<Piece> pieces = new ArrayList<>();
        final List<Rook> whiteRooks = WHITE_ROOK_POSITIONS.stream()
                .map(position -> new Rook(position, Color.WHITE))
                .toList();
        final List<Rook> blackRooks = BLACK_ROOK_POSITIONS.stream()
                .map(position -> new Rook(position, Color.BLACK))
                .toList();
        pieces.addAll(whiteRooks);
        pieces.addAll(blackRooks);
        return pieces;
    }

    @Override
    public Piece copyOf(final Position position) {
        return new Rook(position, getColor());
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
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

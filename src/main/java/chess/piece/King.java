package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class King extends Piece {

    private static final Position WHITE_KING_POSITION = new Position(Row.ONE, Column.E);
    private static final Position BLACK_KING_POSITION = new Position(Row.EIGHT, Column.E);

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

    public King(final Position position, final Color color) {
        super(position, color);
    }

    public static List<Piece> initialize() {
        List<Piece> kings = new ArrayList<>();
        kings.add(new King(WHITE_KING_POSITION, Color.WHITE));
        kings.add(new King(BLACK_KING_POSITION, Color.BLACK));
        return kings;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        final List<Position> positions = new ArrayList<>();
        for (Function<Position, Position> function : MOVEMENTS) {
            Position movedPosition;
            try {
                movedPosition = function.apply(getPosition());
                if (isAvailablePosition(board, movedPosition)) {
                    positions.add(movedPosition);
                }
            } catch (IllegalStateException ignored) {
            }
        }
        return positions;
    }

    @Override
    public Piece copyOf(final Position position) {
        return new King(position, getColor());
    }
}

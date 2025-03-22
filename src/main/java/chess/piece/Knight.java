package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Knight extends Piece {

    private static final List<Position> WHITE_KNIGHT_POSITIONS = List.of(
            new Position(Row.ONE, Column.B), new Position(Row.ONE, Column.G)
    );
    private static final List<Position> BLACK_KNIGHT_POSITIONS = List.of(
            new Position(Row.EIGHT, Column.B), new Position(Row.EIGHT, Column.G)
    );

    private static final List<List<Function<Position,Position>>> MOVEMENTS = List.of(
            List.of(Position::moveUp, Position::moveLeftUp),
            List.of(Position::moveUp, Position::moveRightUp),
            List.of(Position::moveDown, Position::moveLeftDown),
            List.of(Position::moveDown, Position::moveRightDown),
            List.of(Position::moveLeft, Position::moveLeftUp),
            List.of(Position::moveLeft, Position::moveRightUp),
            List.of(Position::moveRight, Position::moveLeftUp),
            List.of(Position::moveRight, Position::moveRightDown)
    );

    public Knight(final Position position, final Color color) {
        super(position, color);
    }

    public static List<Piece> initialize() {
        final List<Piece> pieces = new ArrayList<>();
        final List<Knight> whiteKnights = WHITE_KNIGHT_POSITIONS.stream()
                .map(position -> new Knight(position, Color.WHITE))
                .toList();
        final List<Knight> blackKnights = BLACK_KNIGHT_POSITIONS.stream()
                .map(position -> new Knight(position, Color.BLACK))
                .toList();
        pieces.addAll(whiteKnights);
        pieces.addAll(blackKnights);
        return pieces;
    }

    @Override
    public List<Position> calculateAvailablePositions(final Board board) {
        List<Position> positions = new ArrayList<>();
        for (List<Function<Position, Position>> movement : MOVEMENTS) {
            Position tmp = getPosition().copyOf();
            boolean flag = true;
            for (Function<Position, Position> function : movement) {
                Position movedPosition;
                try {
                    movedPosition = function.apply(tmp);
                } catch (IllegalStateException e) {
                    break;
                }
                if (!isAvailablePosition(board, movedPosition)) {
                    flag = false;
                    break;
                }
                tmp = movedPosition.copyOf();
            }
            if (flag) {
                positions.add(tmp);
            }
        }
        return positions;
    }

    @Override
    public Piece copyOf(final Position position) {
        return new Knight(position, getColor());
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}

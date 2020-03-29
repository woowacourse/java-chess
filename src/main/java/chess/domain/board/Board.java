package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.exceptions.InvalidInputException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;

public class Board {
    private final Map<Position, Optional<Piece>> board;

    public Board(final Map<Position, Optional<Piece>> board) {
        this.board = board;
    }

    public static Board init() {
        return new Board(initialPlacing());
    }

    private static Map<Position, Optional<Piece>> initialPlacing() {
        return Position.getAllPositions()
                .stream()
                .collect(toMap(Function.identity(), Board::findInitialPieceOn));
    }

    private static Optional<Piece> findInitialPieceOn(final Position position) {
        return Piece.getPieces()
                .stream()
                .filter(piece -> piece.canBePlacedOn(position))
                .findAny();
    }

    public Optional<Piece> findPieceBy(final Position position) {
        return board.get(position);
    }

    public Path generatePath(final Position start, final Position end) {
        if (isOnLine(start, end)) {
            return generateLinePath(start, end);
        }
        return generateNoLinePath(start, end);
    }

    private boolean isOnLine(final Position start, final Position end) {
        return Position.columnGap(start, end) == 0
                || Position.rowGap(start, end) == 0
                || Position.columnGap(start, end) == Position.rowGap(start, end);
    }

    private Path generateLinePath(final Position start, final Position end) {
        return new Path(board.keySet()
                .stream()
                .filter(position -> position.inBetween(start, end))
                .collect(toMap(Function.identity(), board::get)), start, end);
    }

    private Path generateNoLinePath(final Position start, final Position end) {
        Position middle = Position.of(start.getRow(), end.getColumn());
        return new Path(board.keySet()
                .stream()
                .filter(position -> position.inBetween(start, middle) || position.inBetween(end, middle))
                .collect(toMap(Function.identity(), board::get)), start, end);
    }

    public void move(final Position start, final Position end) {
        validateNonEmptyStart(start);
        Path path = generatePath(start, end);
        Piece mover = board.get(start).get();
        if (!mover.isMovable(path)) {
            throw new InvalidInputException();
        }
        board.put(end, Optional.of(mover));
        board.put(start, Optional.empty());
    }

    private void validateNonEmptyStart(final Position start) {
        if (!board.get(start).isPresent()) {
            throw new InvalidInputException();
        }
    }

    public long count(final Type type, final Side side) {
        return board.values()
                .stream()
                .filter(piece -> piece.isPresent() && piece.get().is(type, side))
                .count();
    }

    public int countPawnsOnSameColumn(final Side side) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> getPawnCount(side, column))
                .filter(count -> count > 1)
                .sum();
    }

    private int getPawnCount(final Side side, final Column column) {
        return board.keySet()
                .stream()
                .filter(position -> board.get(position).isPresent()
                        && position.isOn(column)
                        && board.get(position).get().is(Type.PAWN, side))
                .collect(summingInt(x -> 1));
    }
}

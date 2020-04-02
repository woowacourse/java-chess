package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.exceptions.InvalidInputException;

public class Board {
    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board init() {
        return new Board(initialPlacement());
    }

    private static Map<Position, Piece> initialPlacement() {
        return Position.getAllPositions()
            .stream()
            .collect(toMap(Function.identity(), Board::findInitialPieceOn));
    }

    private static Piece findInitialPieceOn(final Position position) {
        return Piece.getPieces()
            .stream()
            .filter(piece -> piece.isOnInitialPosition(position))
            .findAny()
            .orElse(Piece.empty());
    }

    public Piece findPieceBy(final Position position) {
        return board.get(position);
    }

    public Path generatePath(final Position start, final Position end) {
        Map<Position, Piece> path = board.keySet()
            .stream()
            .filter(position -> position.existsBetween(start, end))
            .collect(toMap(Function.identity(), board::get));
        return new Path(path, start, end);
    }

    public List<Position> findAllAvailablePath(final Position start) {
        return board.keySet()
            .stream()
            .filter(position -> board.get(start).isMovable(generatePath(start, position)))
            .collect(toList());
    }

    public void move(final Position start, final Position end) {
        validateNonEmptyStart(start);
        Path path = generatePath(start, end);
        Piece mover = board.get(start);
        if (!mover.isMovable(path)) {
            throw new InvalidInputException();
        }
        board.put(end, mover);
        board.put(start, Piece.empty());
    }

    private void validateNonEmptyStart(final Position start) {
        if (board.get(start).isEmpty()) {
            throw new InvalidInputException();
        }
    }

    public boolean isKingDead(Side side) {
        return count(Type.KING, side) == 0;
    }

    public long count(final Type type, final Side side) {
        return board.values()
            .stream()
            .filter(piece -> piece.isNotEmpty() && piece.is(type, side))
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
            .filter(position -> board.get(position).isNotEmpty()
                && position.isOn(column)
                && board.get(position).is(Type.PAWN, side))
            .collect(summingInt(x -> 1));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}

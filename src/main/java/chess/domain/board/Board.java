package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import chess.domain.piece.Piece;

public class Board {
    private final Map<Position, Optional<Piece>> board;

    private Board(final Map<Position, Optional<Piece>> board) {
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

    private static Optional<Piece> findInitialPieceOn(Position position) {
        return Piece.getPieces()
            .stream()
            .filter(piece -> piece.canBePlacedOn(position))
            .findAny();
    }

    public Optional<Piece> findPieceBy(Position position) {
        return board.get(position);
    }

    public Path generatePath(Position start, Position end) {
        if (isOnLine(start, end)) {
            return new Path(board.keySet()
                .stream()
                .filter(position -> position.inBetween(start, end))
                .collect(toMap(Function.identity(), board::get)), start, end);
        }
        Position middle = Position.of(start.getRow(), end.getColumn());
        return new Path(board.keySet()
            .stream()
            .filter(position -> position.inBetween(start, middle) || position.inBetween(end, middle))
            .collect(toMap(Function.identity(), board::get)), start, end);
    }

    public boolean isOnLine(final Position start, final Position end) {
        return Position.columnGap(start, end) == 0
            || Position.rowGap(start, end) == 0
            || Position.columnGap(start, end) == Position.rowGap(start, end);
    }

    public void move(final Position start, final Position end) {
        Path path = generatePath(start, end);
        if (!board.get(start).isPresent()) {
            throw new IllegalArgumentException();
        }
        Piece mover = board.get(start).get();
        if (mover.isMovable(path)) {
            board.put(end, Optional.of(mover));
            board.put(start, Optional.empty());
        }
    }

    // TODO: 게임 끝남 판단 (킹이 잡힌 경우)
    // TODO: 점수 구하기 (Board에서 count & 폰의 위치를 판단하는 로직 필요)
}

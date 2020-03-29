package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@FunctionalInterface
public interface InitialPosition {
    Row WHITE_BIG_PIECE_ROW = Row.ONE;
    Row BLACK_BIG_PIECE_ROW = Row.EIGHT;

    static List<Position> king(final Side side) {
        if (side == Side.WHITE) {
            return Arrays.asList(Position.of(WHITE_BIG_PIECE_ROW, Column.E));
        }
        return Arrays.asList(Position.of(BLACK_BIG_PIECE_ROW, Column.E));
    }

    static List<Position> queen(final Side side) {
        if (side == Side.WHITE) {
            return Arrays.asList(Position.of(WHITE_BIG_PIECE_ROW, Column.D));
        }
        return Arrays.asList(Position.of(BLACK_BIG_PIECE_ROW, Column.D));
    }

    static List<Position> rook(final Side side) {
        if (side == Side.WHITE) {
            return Arrays.asList(Position.of(WHITE_BIG_PIECE_ROW, Column.A),
                    Position.of(WHITE_BIG_PIECE_ROW, Column.H));
        }
        return Arrays.asList(Position.of(BLACK_BIG_PIECE_ROW, Column.A),
                Position.of(BLACK_BIG_PIECE_ROW, Column.H));
    }

    static List<Position> bishop(final Side side) {
        if (side == Side.WHITE) {
            return Arrays.asList(Position.of(WHITE_BIG_PIECE_ROW, Column.C),
                    Position.of(WHITE_BIG_PIECE_ROW, Column.F));
        }
        return Arrays.asList(Position.of(BLACK_BIG_PIECE_ROW, Column.C),
                Position.of(BLACK_BIG_PIECE_ROW, Column.F));
    }

    static List<Position> knight(final Side side) {
        if (side == Side.WHITE) {
            return Arrays.asList(Position.of(WHITE_BIG_PIECE_ROW, Column.B),
                    Position.of(WHITE_BIG_PIECE_ROW, Column.G));
        }
        return Arrays.asList(Position.of(BLACK_BIG_PIECE_ROW, Column.B),
                Position.of(BLACK_BIG_PIECE_ROW, Column.G));
    }

    static List<Position> pawn(final Side side) {
        if (side == Side.WHITE) {
            return Position.getAllPositions().stream()
                    .filter(position -> position.isOn(Row.TWO))
                    .collect(toList());
        }
        return Position.getAllPositions().stream()
                .filter(position -> position.isOn(Row.SEVEN))
                .collect(toList());
    }

    List<Position> findBy(final Side side);
}

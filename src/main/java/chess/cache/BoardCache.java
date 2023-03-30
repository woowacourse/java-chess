package chess.cache;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class BoardCache {
    private static final Map<Position, Piece> board;

    private BoardCache() {
    }

    static {
        board = Arrays.stream(Column.values())
                .flatMap(BoardCache::createPositionWithRow)
                .collect(Collectors.toMap(Function.identity(), BoardCache::createEmpty));
    }

    private static Stream<Position> createPositionWithRow(final Column column) {
        return Arrays.stream(Row.values()).map(row -> Position.of(row, column));
    }

    private static Piece createEmpty(Position ignore) {
        return Empty.create();
    }

    public static Map<Position, Piece> create() {
        return Map.copyOf(board);
    }
}

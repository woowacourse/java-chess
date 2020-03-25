package chess.domain.board;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.params.provider.Arguments;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;

public class Fixture {
    public static final Arguments PATH_EXCLUDING_START_POINT = Arguments.of(
        "시작점과 끝점이 경로 상에 존재하지 않으면 예외 발생",
        new HashMap<Position, Optional<Piece>>() {{
            put(Position.of(Row.TWO, Column.A), Optional.of(Piece.of(Type.QUEEN, Side.BLACK)));
            put(Position.of(Row.THREE, Column.D), Optional.empty());
        }}, Position.of(Row.ONE, Column.A), Position.of(Row.FOUR, Column.D));

    public static final Arguments EMPTY_STARTING_POINT = Arguments.of(
        "시작점과 비어있으면 예외 발생",
        new HashMap<Position, Optional<Piece>>() {{
            put(Position.of(Row.ONE, Column.A), Optional.empty());
            put(Position.of(Row.FOUR, Column.D), Optional.empty());
        }}, Position.of(Row.ONE, Column.A), Position.of(Row.FOUR, Column.D));

    public static final Path NO_BLOCKING_ENEMY_ON_END = new Path(new HashMap<Position, Optional<Piece>>() {{
        put(Position.of(Row.ONE, Column.A), Optional.of(Piece.of(Type.QUEEN, Side.BLACK)));
        put(Position.of(Row.TWO, Column.B), Optional.empty());
        put(Position.of(Row.THREE, Column.C), Optional.empty());
        put(Position.of(Row.FOUR, Column.D), Optional.of(Piece.of(Type.QUEEN, Side.WHITE)));
    }}, Position.of(Row.ONE, Column.A), Position.of(Row.FOUR, Column.D));
}

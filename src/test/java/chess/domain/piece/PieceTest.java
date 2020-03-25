package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class PieceTest {
    @DisplayName("체스 말 move test")
    @ParameterizedTest(name = "{0}")
    @MethodSource("movableParams")
    void movable(String message, Type type, Side side, Position start, Position end, boolean expected) {
        // TODO: 임시로 path 대신 new HashMap() 추가함
        assertThat(Piece.of(type, Side.BLACK).isMovable(start, end, new HashMap<>())).isEqualTo(expected);
    }

    static Stream<Arguments> movableParams() {
        return Stream.of(
            of(
                "시작과 끝이 같은 경우 false",
                Type.QUEEN, Side.BLACK,
                Position.of(Row.FOUR, Column.B), Position.of(Row.FOUR, Column.B),
                false
            ),
            of(
                "시작과 끝이 다른 경우 true",
                Type.QUEEN, Side.BLACK,
                Position.of(Row.FOUR, Column.C), Position.of(Row.FOUR, Column.B),
                true
            ),
            of(
                "King의 정상적인 이동",
                Type.KING, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.TWO, Column.B),
                true
            ),
            of(
                "King의 비정상적인 이동",
                Type.KING, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.B),
                false
            ),
            of(
                "Rook의 정상적인 이동",
                Type.ROOK, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.A),
                true
            ),
            of(
                "Rook의 비정상적인 이동",
                Type.ROOK, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.B),
                false
            ),
            of(
                "Bishop의 정상적인 이동",
                Type.BISHOP, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.H),
                true
            ),
            of(
                "Bishop의 비정상적인 이동",
                Type.BISHOP, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.EIGHT, Column.A),
                false
            ),
            of(
                "Queen의 직선 이동",
                Type.QUEEN, Side.BLACK,
                Position.of(Row.EIGHT, Column.C), Position.of(Row.ONE, Column.C),
                true
            ),
            of(
                "Queen의 대각선 이동",
                Type.QUEEN, Side.BLACK,
                Position.of(Row.EIGHT, Column.A), Position.of(Row.ONE, Column.H),
                true
            ),
            of(
                "Queen의 비정상적인 이동",
                Type.QUEEN, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.B),
                false
            ),
            of(
                "Knight의 정상적인 이동",
                Type.KNIGHT, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.B),
                true
            ),
            of(
                "Knight의 비정상적인 이동",
                Type.KNIGHT, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.C),
                false
            ),
            of(
                "Black Pawn의 정상적인 이동",
                Type.KNIGHT, Side.BLACK,
                Position.of(Row.EIGHT, Column.A), Position.of(Row.SEVEN, Column.A),
                true
            ),
            of(
                "Black Pawn의 비정상적인 이동",
                Type.KNIGHT, Side.BLACK,
                Position.of(Row.SEVEN, Column.A), Position.of(Row.EIGHT, Column.A),
                false
            ),
            of(
                "Black/White Pawn의 비정상적인 이동",
                Type.KNIGHT, Side.BLACK,
                Position.of(Row.ONE, Column.A), Position.of(Row.THREE, Column.C),
                false
            )
        );
    }
}

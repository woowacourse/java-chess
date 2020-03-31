package chess.domain.piece;

import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

class DirectionTest {

    @ParameterizedTest
    @DisplayName("주어진 방향으로 이동했을 때의 포지션")
    @MethodSource("createDirection")
    void findDestination(Direction direction, Position expected) {
        Column column = direction.findColumnDestination(Column.D).orElse(null);
        Row row = direction.findRowDestination(Row.FIVE).orElse(null);

        assertThat(Position.of(column, row)).isEqualTo(expected);
    }

    static Stream<Arguments> createDirection() {
        return Stream.of(
                Arguments.of(N, Position.from("d6")),
                Arguments.of(NE, Position.from("e6")),
                Arguments.of(E, Position.from("e5")),
                Arguments.of(SE, Position.from("e4")),
                Arguments.of(S, Position.from("d4")),
                Arguments.of(SW, Position.from("c4")),
                Arguments.of(W, Position.from("c5")),
                Arguments.of(NW, Position.from("c6")),
                Arguments.of(NNE, Position.from("e7")),
                Arguments.of(NEE, Position.from("f6")),
                Arguments.of(SEE, Position.from("f4")),
                Arguments.of(SSE, Position.from("e3")),
                Arguments.of(SSW, Position.from("c3")),
                Arguments.of(SWW, Position.from("b4")),
                Arguments.of(NWW, Position.from("b6")),
                Arguments.of(NNW, Position.from("c7"))
        );
    }
}
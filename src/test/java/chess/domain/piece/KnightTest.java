package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

    private Knight knight;

    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("b7")),
                Arguments.of(Position.from("d7")),
                Arguments.of(Position.from("e6")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("d3")),
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("a4")),
                Arguments.of(Position.from("a6"))
        );
    }

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.BLACK, Position.from("c5"));
    }

    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        knight.moveToEmpty(position, new Pieces());
        assertTrue(knight.hasPosition(position));
    }

    @Test
    @DisplayName("나이트 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = knight.movablePositions(position).get(0);
        assertThat(positions).contains(
                Position.from("b7"),
                Position.from("d7"),
                Position.from("e6"),
                Position.from("e4"),
                Position.from("d3"),
                Position.from("b3"),
                Position.from("a4"),
                Position.from("a6")
        );
    }
}
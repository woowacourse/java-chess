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

class KingTest {
    private static Stream<Arguments> destinations() {
        return Stream.of(
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("c6")),
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("b5")),
                Arguments.of(Position.from("d5")),
                Arguments.of(Position.from("b4")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4"))
        );
    }
    private King king;

    @BeforeEach
    void setUp() {
        king = new King(Color.BLACK, Position.from("c5"));
    }


    @ParameterizedTest
    @MethodSource("destinations")
    void move(Position position) {
        king.moveToEmpty(position, new Pieces());
        assertTrue(king.hasPosition(position));
    }

    @Test
    @DisplayName("킹 이동 가능한 위치 값 들 확인")
    void possiblePositions() {
        Position position = Position.from("c5");
        List<Position> positions = king.movablePositions(position);
        assertThat(positions).contains(
                Position.from("b6"),
                Position.from("c6"),
                Position.from("d6"),
                Position.from("b5"),
                Position.from("d5"),
                Position.from("b4"),
                Position.from("c4"),
                Position.from("d4")
        );
    }
}
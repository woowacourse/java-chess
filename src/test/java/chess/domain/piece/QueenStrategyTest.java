package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

class QueenStrategyTest {

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("d4"),
                        Arrays.asList(Position.from("b2"), Position.from("c3"))),
                Arguments.of(Position.from("d4"), Position.from("a1"),
                        Arrays.asList(Position.from("c3"), Position.from("b2"))),
                Arguments.of(Position.from("b2"), Position.from("f6"),
                        Arrays.asList(Position.from("c3"), Position.from("d4"), Position.from("e5"))),
                Arguments.of(Position.from("f6"), Position.from("b2"),
                        Arrays.asList(Position.from("e5"), Position.from("d4"), Position.from("c3"))),
                Arguments.of(Position.from("g5"), Position.from("f4"), Collections.emptyList()),
                Arguments.of(Position.from("a1"), Position.from("a4"),
                        Arrays.asList(Position.from("a2"), Position.from("a3"))),
                Arguments.of(Position.from("a4"), Position.from("a1"),
                        Arrays.asList(Position.from("a3"), Position.from("a2"))),
                Arguments.of(Position.from("b2"), Position.from("f2"),
                        Arrays.asList(Position.from("c2"), Position.from("d2"), Position.from("e2"))),
                Arguments.of(Position.from("f2"), Position.from("b2"),
                        Arrays.asList(Position.from("e2"), Position.from("d2"), Position.from("c2"))),
                Arguments.of(Position.from("g5"), Position.from("g4"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position source, Position target, List<Position> expected) {
        MoveStrategy queenStrategy = new QueenStrategy();
        assertThat(queenStrategy.findMovePath(source, target)).isEqualTo(expected);
    }

    @Test
    @DisplayName("이동할 수 없는 source, target")
    void invalidMovementException() {
        MoveStrategy queenStrategy = new QueenStrategy();

        Position source = Position.from("a1");
        Position target = Position.from("d3");

        assertThatThrownBy(() -> {
            queenStrategy.findMovePath(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }
}
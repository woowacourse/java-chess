package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnStrategyTest {

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position target, List<Position> expected) {
        Position source = Position.from("d2");
        MoveStrategy pawnStrategy = new PawnStrategy();
        assertThat(pawnStrategy.findMovePath(source, target)).isEqualTo(expected);
    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("d3"), Collections.emptyList()),
                Arguments.of(Position.from("d4"), Collections.singletonList(Position.from("d3")))
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        MoveStrategy pawnStrategy = new PawnStrategy();

        Position source = Position.from("d5");

        assertThatThrownBy(() -> {
            pawnStrategy.findMovePath(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("c6")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e6"))
        );
    }

    @ParameterizedTest
    @DisplayName("kill일 경우 이동 경로 찾기")
    @MethodSource("createKillSourceToTarget")
    void findKillPath(Position target, List<Position> expected) {
        Position source = Position.from("d2");
        MoveStrategy pawnStrategy = new PawnStrategy();
        assertThat(pawnStrategy.findKillPath(source, target)).isEqualTo(expected);
    }

    static Stream<Arguments> createKillSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("c3"), Collections.emptyList()),
                Arguments.of(Position.from("e3"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("kill일 경우 이동할 수 없는 source, target")
    @MethodSource("createKillInvalidTarget")
    void invalidKillMovementException(Position target) {
        MoveStrategy pawnStrategy = new PawnStrategy();

        Position source = Position.from("d5");

        assertThatThrownBy(() -> {
            pawnStrategy.findKillPath(source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.");
    }

    static Stream<Arguments> createKillInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("d6")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("e5"))
        );
    }
}
package chess.domain.board;

import chess.domain.position.ChessRank;
import chess.domain.position.Direction;
import chess.domain.position.Movement;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class MovementTest {

    static Stream<Arguments> findRouteArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("d1"), Set.of(Position.of("d3"), Position.of("d2"))),
                Arguments.arguments(Position.of("d4"), Position.of("g4"), Set.of(Position.of("e4"), Position.of("f4"))),
                Arguments.arguments(Position.of("d4"), Position.of("d7"), Set.of(Position.of("d5"), Position.of("d6"))),
                Arguments.arguments(Position.of("d4"), Position.of("a4"), Set.of(Position.of("c4"), Position.of("b4"))),
                Arguments.arguments(Position.of("d4"), Position.of("g7"), Set.of(Position.of("e5"), Position.of("f6"))),
                Arguments.arguments(Position.of("d4"), Position.of("a7"), Set.of(Position.of("c5"), Position.of("b6"))),
                Arguments.arguments(Position.of("d4"), Position.of("g1"), Set.of(Position.of("e3"), Position.of("f2"))),
                Arguments.arguments(Position.of("d4"), Position.of("a1"), Set.of(Position.of("c3"), Position.of("b2"))),
                Arguments.arguments(Position.of("d4"), Position.of("e6"), Set.of(Position.of("d5"), Position.of("d6")))
        );
    }

    static Stream<Arguments> findDirectionArguments() {
        return Stream.of(
                Arguments.arguments(Position.of("d4"), Position.of("d1"), Direction.DOWN),
                Arguments.arguments(Position.of("d4"), Position.of("g4"), Direction.RIGHT),
                Arguments.arguments(Position.of("d4"), Position.of("d7"), Direction.UP),
                Arguments.arguments(Position.of("d4"), Position.of("a4"), Direction.LEFT),
                Arguments.arguments(Position.of("d4"), Position.of("g7"), Direction.UP_RIGHT),
                Arguments.arguments(Position.of("d4"), Position.of("a7"), Direction.UP_LEFT),
                Arguments.arguments(Position.of("d4"), Position.of("g1"), Direction.DOWN_RIGHT),
                Arguments.arguments(Position.of("d4"), Position.of("a1"), Direction.DOWN_LEFT)
        );
    }

    @DisplayName("source부터 target으로 가기까지의 경로를 찾는다.")
    @ParameterizedTest
    @MethodSource("findRouteArguments")
    void findRoute(Position source, Position target, Set<Position> expected) {
        //given
        Movement movement = new Movement(source, target);

        //when
        Set<Position> result = movement.findRouteBetween();

        //then
        assertThat(result).hasSize(expected.size()).containsAll(expected);
    }

    @DisplayName("대각선으로 움직인다.")
    @Test
    void isDiagonal() {
        //given
        Position source = Position.of("b3");
        Position target = Position.of("c4");
        Movement movement = new Movement(source, target);

        //when & then
        assertThat(movement.isDiagonal()).isTrue();
    }

    @DisplayName("상하좌우로 움직인다.")
    @Test
    void isCross() {
        //given
        Position source = Position.of("b3");
        Position target = Position.of("c3");
        Movement movement = new Movement(source, target);

        //when & then
        assertThat(movement.isCross()).isTrue();
    }

    @DisplayName("source에서 target으로의 방향을 찾는다.")
    @ParameterizedTest
    @MethodSource("findDirectionArguments")
    void findDirection(Position source, Position target, Direction expected) {
        //given
        Movement movement = new Movement(source, target);

        //when & then
        assertThat(movement.findDirection()).isEqualTo(expected);
    }

    @DisplayName("source의 rank가 주어진 rank와 동일하다.")
    @Test
    void isSameSourceRank() {
        //given
        Position source = Position.of("b7");
        Position target = Position.of("b3");
        Movement movement = new Movement(source, target);
        ChessRank rank = ChessRank.SEVEN;

        //when & then
        assertThat(movement.isSourceRank(rank)).isTrue();
    }

    @DisplayName("source의 rank가 주어진 rank와 다르다.")
    @Test
    void isNotSameSourceRank() {
        //given
        Position source = Position.of("a3");
        Position target = Position.of("b3");
        Movement movement = new Movement(source, target);
        ChessRank rank = ChessRank.SEVEN;

        //when & then
        assertThat(movement.isSourceRank(rank)).isFalse();
    }
}

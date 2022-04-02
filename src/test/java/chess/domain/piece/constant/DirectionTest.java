package chess.domain.piece.constant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.Position;

class DirectionTest {

    @DisplayName("출발지부터 목적지까지의 단방향 이동경로를 계산할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 방향 : {0}, 출발지 : {1}, 목적지 : {2}, 이동경로 : {3}")
    @MethodSource("provideForCalculateRoute")
    void calculateRoute(final Direction direction,
                        final Position source,
                        final Position target,
                        final List<Position> expectedRoute) {
        final List<Position> actualRoute = direction.calculateUnidirectionalRoute(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    private static Stream<Arguments> provideForCalculateRoute() {
        return Stream.of(
                Arguments.of(
                        Named.of("상향", Direction.UP),
                        Position.from("a1"),
                        Position.from("c4"),
                        Collections.emptyList()
                ),
                Arguments.of(
                        Named.of("상향", Direction.UP),
                        Position.from("a1"),
                        Position.from("a4"),
                        List.of(Position.from("a2"), Position.from("a3"), Position.from("a4"))
                ),
                Arguments.of(
                        Named.of("우상향", Direction.UP_RIGHT),
                        Position.from("a1"),
                        Position.from("d4"),
                        List.of(Position.from("b2"), Position.from("c3"), Position.from("d4"))
                )
        );
    }
}
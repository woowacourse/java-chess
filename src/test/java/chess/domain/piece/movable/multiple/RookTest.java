package chess.domain.piece.movable.multiple;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.Position;

class RookTest {

    private Rook rook;

    @BeforeEach
    void setUp() {
        rook = Rook.getInstance();
    }

    @DisplayName("룩은 상하좌우 방향 외의 위치로 이동할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForWrongDirectionException")
    void moveToWrongDirectionException(final Position source, final Position target) {
        assertThatThrownBy(() -> rook.calculateRouteToMove(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    @DisplayName("룩은 상하좌우 방향 외의 위치로 공격할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForWrongDirectionException")
    void attackToWrongDirectionException(final Position source, final Position target) {
        assertThatThrownBy(() -> rook.calculateRouteToAttack(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    private static Stream<Arguments> provideForWrongDirectionException() {
        return Stream.of(
                Arguments.of(
                        Position.from("a2"),
                        Position.from("d4")
                ),
                Arguments.of(
                        Position.from("d2"),
                        Position.from("b3")
                )
        );
    }

    @DisplayName("룩은 상하좌우 방향의 위치으로 이동할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
    @MethodSource("provideForCalculateRoute")
    void calculateRouteToMove(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = rook.calculateRouteToMove(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    @DisplayName("룩은 상하좌우 방향의 위치으로 공격할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
    @MethodSource("provideForCalculateRoute")
    void calculateRouteToAttack(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = rook.calculateRouteToAttack(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    private static Stream<Arguments> provideForCalculateRoute() {
        return Stream.of(
                Arguments.of(
                        Position.from("a1"),
                        Position.from("a4"),
                        List.of(Position.from("a2"), Position.from("a3"), Position.from("a4"))
                ),
                Arguments.of(
                        Position.from("d1"),
                        Position.from("b1"),
                        List.of(Position.from("c1"), Position.from("b1"))
                )
        );
    }

    @DisplayName("룩은 폰이 아니어야 한다.")
    @Test
    void isPawn() {
        assertThat(rook.isPawn()).isFalse();
    }

    @DisplayName("룩은 킹이 아니어야 한다.")
    @Test
    void isKing() {
        assertThat(rook.isKing()).isFalse();
    }

    @DisplayName("룩의 기물 이름은 Bishop 이어야 한다.")
    @Test
    void getPieceName() {
        final String actual = rook.getPieceName();
        final String expected = "Rook";
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("룩의 기물 점수는 5점 이어야 한다.")
    @Test
    void getPieceScore() {
        final double actual = rook.getPieceScore();
        final double expected = 5;
        assertThat(actual).isEqualTo(expected);
    }
}
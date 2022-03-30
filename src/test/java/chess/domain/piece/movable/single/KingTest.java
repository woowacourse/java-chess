package chess.domain.piece.movable.single;

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

class KingTest {

    private King king;

    @BeforeEach
    void setUp() {
        king = King.getInstance();
    }

    @DisplayName("킹은 상하좌우, 대각선 방향 외의 위치로 이동할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForWrongDirectionException")
    void moveToWrongDirectionException(final Position source, final Position target) {
        assertThatThrownBy(() -> king.calculateRouteToMove(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    @DisplayName("킹은 상하좌우, 대각선 방향 외의 위치로 공격할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForWrongDirectionException")
    void attackToWrongDirectionException(final Position source, final Position target) {
        assertThatThrownBy(() -> king.calculateRouteToAttack(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    private static Stream<Arguments> provideForWrongDirectionException() {
        return Stream.of(
                Arguments.of(
                        Position.from("a1"),
                        Position.from("b3")
                ),
                Arguments.of(
                        Position.from("d1"),
                        Position.from("a3")
                )
        );
    }

    @DisplayName("킹은 상하좌우, 대각선 방향으로 2칸 이상의 범위로 이동할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForOutOfRangePositionException")
    void moveToOutOfRangePositionException(final Position source, final Position target) {
        assertThatThrownBy(() -> king.calculateRouteToMove(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    @DisplayName("킹은 상하좌우, 대각선 방향으로 2칸 이상의 범위로 공격할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
    @MethodSource("provideForOutOfRangePositionException")
    void attackToOutOfRangePositionException(final Position source, final Position target) {
        assertThatThrownBy(() -> king.calculateRouteToAttack(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 가능한 경로가 없습니다.");
    }

    private static Stream<Arguments> provideForOutOfRangePositionException() {
        return Stream.of(
                Arguments.of(
                        Position.from("a1"),
                        Position.from("a3")
                ),
                Arguments.of(
                        Position.from("a1"),
                        Position.from("c3")
                )
        );
    }

    @DisplayName("킹은 상하좌우, 대각선 방향으로 1칸 이내의 범위로 이동할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
    @MethodSource("provideForCalculateRoute")
    void moveToOutOfRangeException(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = king.calculateRouteToMove(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    @DisplayName("킹은 상하좌우, 대각선 방향으로 1칸 이내의 범위로 공격할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
    @MethodSource("provideForCalculateRoute")
    void attackToOutOfRangeException(final Position source, final Position target, final List<Position> expectedRoute) {
        final List<Position> actualRoute = king.calculateRouteToAttack(source, target);
        assertThat(actualRoute).isEqualTo(expectedRoute);
    }

    private static Stream<Arguments> provideForCalculateRoute() {
        return Stream.of(
                Arguments.of(
                        Position.from("a1"),
                        Position.from("a2"),
                        List.of(Position.from("a2"))
                ),
                Arguments.of(
                        Position.from("a1"),
                        Position.from("b2"),
                        List.of(Position.from("b2"))
                ),
                Arguments.of(
                        Position.from("a1"),
                        Position.from("b1"),
                        List.of(Position.from("b1"))
                )
        );
    }

    @DisplayName("킹은 폰이 아니어야 한다.")
    @Test
    void isPawn() {
        assertThat(king.isPawn()).isFalse();
    }

    @DisplayName("킹은 킹이어야 한다.")
    @Test
    void isKing() {
        assertThat(king.isKing()).isTrue();
    }

    @DisplayName("킹의 기물 이름은 King 이어야 한다.")
    @Test
    void getPieceName() {
        final String actual = king.getPieceName();
        final String expected = "King";
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("킹의 기물 점수는 0점 이어야 한다.")
    @Test
    void getPieceScore() {
        final double actual = king.getPieceScore();
        final double expected = 0;
        assertThat(actual).isEqualTo(expected);
    }
}
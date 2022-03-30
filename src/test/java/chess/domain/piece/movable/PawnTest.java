package chess.domain.piece.movable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.Position;

class PawnTest {

    private Pawn whitePawn;

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.getWhitePawn();
    }

    @DisplayName("폰은 킹이어야 한다.")
    @Test
    void isPawn() {
        assertThat(whitePawn.isPawn()).isTrue();
    }

    @DisplayName("폰은 킹이 아니어야 한다.")
    @Test
    void isKing() {
        assertThat(whitePawn.isKing()).isFalse();
    }

    @DisplayName("폰의 기물 이름은 Pawn 이어야 한다.")
    @Test
    void getPieceName() {
        final String actual = whitePawn.getPieceName();
        final String expected = "Pawn";
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("폰의 기물 점수는 1점 이어야 한다.")
    @Test
    void getPieceScore() {
        final double actual = whitePawn.getPieceScore();
        final double expected = 1;
        assertThat(actual).isEqualTo(expected);
    }

    @TestInstance(Lifecycle.PER_CLASS)
    @Nested
    class WhitePawnTest {

        private Pawn whitePawn;

        @BeforeEach
        void setUp() {
            whitePawn = Pawn.getWhitePawn();
        }

        @DisplayName("흰색 폰은 첫번째 움직임에서 2칸 이내의 상 방향 범위를 벗어나 이동할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a1,a4", "a1,b2", "a1,b1"})
        void firstMoveToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> whitePawn.calculateRouteToMove(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("흰색 폰은 첫번째 움직임 이후부터 1칸 이내의 상 방향 범위를 벗어나 이동할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a1,a3", "a1,b2", "a1,b1"})
        void moveToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> whitePawn.move().calculateRouteToMove(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("흰색 폰은 1칸 이내의 우상, 좌상 방향 범위를 벗어나 공격할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a1,a2", "a1,c3", "a1,b1"})
        void attackToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> whitePawn.move().calculateRouteToAttack(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("흰색 폰은 첫번째 움직임에서 2칸 이내의 상 방향으로 이동할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToFirstMove")
        void calculateRouteToFirstMove(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = whitePawn.calculateRouteToMove(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToFirstMove() {
            return Stream.of(
                    Arguments.of(
                            Position.from("a1"),
                            Position.from("a2"),
                            List.of(Position.from("a2"))
                    ),
                    Arguments.of(
                            Position.from("a1"),
                            Position.from("a3"),
                            List.of(Position.from("a2"), Position.from("a3"))
                    )
            );
        }

        @DisplayName("흰색 폰은 첫번째 움직임 이후부터 1칸 이내의 상 방향으로 이동할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToMove")
        void calculateRouteToMove(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = whitePawn.calculateRouteToMove(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToMove() {
            return Stream.of(
                    Arguments.of(
                            Position.from("a1"),
                            Position.from("a2"),
                            List.of(Position.from("a2"))
                    )
            );
        }

        @DisplayName("흰색 폰은 1칸 이내의 우상, 좌상 방향으로 공격할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToAttack")
        void calculateRouteToAttack(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = whitePawn.calculateRouteToAttack(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToAttack() {
            return Stream.of(
                    Arguments.of(
                            Position.from("b1"),
                            Position.from("a2"),
                            List.of(Position.from("a2"))
                    ),
                    Arguments.of(
                            Position.from("b1"),
                            Position.from("c2"),
                            List.of(Position.from("c2"))
                    )
            );
        }
    }

    @TestInstance(Lifecycle.PER_CLASS)
    @Nested
    class BlackPawnTest {

        private Pawn blackPawn;

        @BeforeEach
        void setUp() {
            blackPawn = Pawn.getBlackPawn();
        }

        @DisplayName("검은색 폰은 첫번째 움직임에서 2칸 이내의 하 방향 범위를 벗어나 이동할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a8,a5", "a8,b7", "a8,b8"})
        void firstMoveToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> blackPawn.calculateRouteToMove(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("검은색 폰은 첫번째 움직임 이후부터 1칸 이내의 하 방향 범위를 벗어나 이동할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a8,a6", "a8,b7", "a8,b8"})
        void moveToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> blackPawn.move().calculateRouteToMove(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("검은색 폰은 1칸 이내의 우하, 좌하 방향 범위를 벗어나 공격할 수 없다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}")
        @CsvSource(value = {"a8,a7", "a8,c6", "a8,b8"})
        void attackToOutOfRangeException(final String source, final String target) {
            assertThatThrownBy(() -> blackPawn.move().calculateRouteToAttack(Position.from(source), Position.from(target)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("이동 가능한 경로가 없습니다.");
        }

        @DisplayName("검은색 폰은 첫번째 움직임에서 2칸 이내의 하 방향으로 이동할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToFirstMove")
        void calculateRouteToFirstMove(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = blackPawn.calculateRouteToMove(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToFirstMove() {
            return Stream.of(
                    Arguments.of(
                            Position.from("a8"),
                            Position.from("a7"),
                            List.of(Position.from("a7"))
                    ),
                    Arguments.of(
                            Position.from("a8"),
                            Position.from("a6"),
                            List.of(Position.from("a7"), Position.from("a6"))
                    )
            );
        }

        @DisplayName("검은색 폰은 첫번째 움직임 이후부터 1칸 이내의 하 방향으로 이동할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToMove")
        void calculateRouteToMove(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = blackPawn.calculateRouteToMove(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToMove() {
            return Stream.of(
                    Arguments.of(
                            Position.from("a8"),
                            Position.from("a7"),
                            List.of(Position.from("a7"))
                    )
            );
        }

        @DisplayName("검은색 폰은 1칸 이내의 우하, 좌하 방향으로 공격할 수 있다.")
        @ParameterizedTest(name = "[{index}] 출발지 : {0}, 도착지 : {1}, 이동경로 : {2}")
        @MethodSource("provideForCalculateRouteToAttack")
        void calculateRouteToAttack(final Position source, final Position target, final List<Position> expectedRoute) {
            final List<Position> actualRoute = blackPawn.calculateRouteToAttack(source, target);
            assertThat(actualRoute).isEqualTo(expectedRoute);
        }

        private Stream<Arguments> provideForCalculateRouteToAttack() {
            return Stream.of(
                    Arguments.of(
                            Position.from("b8"),
                            Position.from("a7"),
                            List.of(Position.from("a7"))
                    ),
                    Arguments.of(
                            Position.from("b8"),
                            Position.from("c7"),
                            List.of(Position.from("c7"))
                    )
            );
        }
    }
}
package chess.domain.piece.strategy;

import chess.domain.piece.MoveStrategy;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.position.PiecePositionFixture.piecePositions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("PawnMoveStrategyTest 은")
class PawnMoveStrategyTest {

    @Nested
    class 기본적인_경우 {

        private final MoveStrategy strategy = new PawnMoveStrategy();
        private final PiecePosition source = PiecePosition.of("e4");

        @Nested
        class 북쪽_혹은_남쪽_방향으로_직선_두_칸_이동_시 {

            @ParameterizedTest(name = "움직일 수 있다. [e4] -> [{1}]")
            @CsvSource({"e6", "e2"})
            void 움직일_수_있다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isTrue();
            }

            @ParameterizedTest(name = "경유지를 반환한다. 출발: [e4] -> 경유지: [{0}] -> 도착: [{1}]")
            @CsvSource({
                    "e5, e6",
                    "e3, e2"
            })
            void 경유지를_반환한다(final PiecePosition waypoint, final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.waypoints(path)).containsOnly(waypoint);
            }
        }

        @Nested
        class 북쪽_혹은_남쪽으로_한_칸_이동하거나_대각선으로_한_칸_이동_시 {

            @ParameterizedTest(name = "움직일 수 있다. [e4] -> [{0}]")
            @CsvSource({"e3", "e5", "d3", "d5", "f3", "f5",})
            void 움직일_수_있다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isTrue();
            }

            @ParameterizedTest(name = "경유지는 없다. [e4] -> [{0}]")
            @CsvSource({"e3", "e5", "d3", "d5", "f3", "f5",})
            void 경유지는_없다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.waypoints(path)).isEmpty();
            }
        }

        @Nested
        class 이외의_경로로_이동_시 {

            @ParameterizedTest(name = "움직일 수 없다. [e4] -> [{1}]")
            @CsvSource({"d4", "f4", "e7", "c4", "g4", "c2", "c6", "g2", "g6"})
            void 움직일_수_없다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isFalse();
            }

            @ParameterizedTest(name = "경유지를 조회하면 예외. [e4] -> [{1}]")
            @CsvSource({"d4", "f4", "e7", "c4", "g4", "c2", "c6", "g2", "g6"})
            void 경유지를_조회하면_예외(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThatThrownBy(() -> strategy.waypoints(path))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("추가 전략을 합쳐서 사용하는 경우")
    class WithAdditionalStrategy {

        private final PiecePosition source = PiecePosition.of("e4");

        @Nested
        @DisplayName("추가 조건은 지키지만 기본 조건을 지키지 못한 경로로 이동하는 경우")
        class ValidAdditionalButInvalidDefault {

            final PawnMoveStrategy strategy = new PawnMoveStrategy(path -> true);

            @ParameterizedTest(name = "움직일 수 없다. [e4] -> [{1}]")
            @CsvSource({"d4", "f4", "e7", "c4", "g4", "c2", "c6", "g2", "g6"})
            void 움직일_수_없다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isFalse();
            }

            @ParameterizedTest(name = "경유지를 조회하면 예외. [e4] -> [{1}]")
            @CsvSource({"d4", "f4", "e7", "c4", "g4", "c2", "c6", "g2", "g6"})
            void 경유지를_조회하면_예외(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThatThrownBy(() -> strategy.waypoints(path))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("기본 조건은 지키지만 추가 조건을 지키지 못한 경로로 이동하는 경우")
        class ValidDefaultButInvalidAdditional {

            final PawnMoveStrategy strategy = new PawnMoveStrategy(path -> false);

            @ParameterizedTest(name = "움직일 수 없다. [e4] -> [{1}]")
            @CsvSource({"e3", "e5", "d3", "d5", "f3", "f5", "e6", "e2",})
            void 움직일_수_없다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isFalse();
            }

            @ParameterizedTest(name = "경유지를 조회하면 예외. [e4] -> [{1}]")
            @CsvSource({"e3", "e5", "d3", "d5", "f3", "f5", "e6", "e2",})
            void 경유지를_조회하면_예외(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThatThrownBy(() -> strategy.waypoints(path))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }

        @Nested
        @DisplayName("기본 조건과 추가 조건을 모두 지킨 경우")
        @TestInstance(TestInstance.Lifecycle.PER_CLASS)
        class ValidAll {

            final PawnMoveStrategy strategy = new PawnMoveStrategy(path -> true);

            @ParameterizedTest(name = "움직일 수 있다. [e4] -> [{0}]")
            @CsvSource({"e3", "e5", "d3", "d5", "f3", "f5", "e6", "e2",})
            void 움직일_수_있다(final PiecePosition destination) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.movable(path)).isTrue();
            }

            @ParameterizedTest(name = "경유지를 반환한다. 출발: [e4] -> 경유지: [{1}] -> 도착: [{0}]")
            @MethodSource("pawnDestinations")
            void 경유지를_반환한다(final PiecePosition destination, final List<PiecePosition> waypoints) {
                // given
                final Path path = Path.of(source, destination);

                // when & then
                assertThat(strategy.waypoints(path)).containsExactlyInAnyOrderElementsOf(waypoints);
            }

            Stream<Arguments> pawnDestinations() {
                return Stream.of(
                        Arguments.of("e3", Named.of("X", Collections.emptyList())),
                        Arguments.of("e5", Named.of("X", Collections.emptyList())),
                        Arguments.of("d3", Named.of("X", Collections.emptyList())),
                        Arguments.of("d5", Named.of("X", Collections.emptyList())),
                        Arguments.of("f3", Named.of("X", Collections.emptyList())),
                        Arguments.of("e6", Named.of("e5", piecePositions("e5"))),
                        Arguments.of("e2", Named.of("e3", piecePositions("e3")))
                );
            }
        }
    }
}

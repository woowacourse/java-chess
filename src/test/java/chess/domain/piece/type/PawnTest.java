package chess.domain.piece.type;

import chess.domain.piece.Color;
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
@DisplayName("Pawn 은")
class PawnTest {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 흰색_폰_움직임_테스트 {

        @ParameterizedTest(name = "움직이지 않은 경우 북쪽을 향해 직진 한칸, 두칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [b2] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
        @MethodSource("noneMovePawnDestinations")
        void 움직이지_않은_경우_북쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThat(pawn.waypoints(destination).wayPoints())
                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
        }

        Stream<Arguments> noneMovePawnDestinations() {
            return Stream.of(
                    Arguments.of("b3", Named.of("b3", piecePositions("b3"))),
                    Arguments.of("b4", Named.of("b3, b4", piecePositions("b3", "b4"))),
                    Arguments.of("a3", Named.of("X", Collections.emptyList())),
                    Arguments.of("c3", Named.of("X", Collections.emptyList()))
            );
        }

        @ParameterizedTest(name = "움직인 경우 북쪽을 향해 직진 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [b2] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
        @MethodSource("movedPawnDestinations")
        void 움직인_경우_북쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);
            pawn.move(PiecePosition.of(4, 'b'));

            // when & then
            assertThat(pawn.waypoints(destination).wayPoints())
                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
        }

        Stream<Arguments> movedPawnDestinations() {
            return Stream.of(
                    Arguments.of("b5", Named.of("b3", piecePositions("b5"))),
                    Arguments.of("a5", Named.of("X", Collections.emptyList())),
                    Arguments.of("c5", Named.of("X", Collections.emptyList()))
            );
        }

        @ParameterizedTest(name = "어떠한 경우에도 세 칸 이상은 이동할 수 없다. 예를 들어 [b2] 에서 [{0}] 으로는 이동이 불가능하다. ")
        @CsvSource({
                "b5",
                "b6",
                "c4",
                "a4",
        })
        void 모든_경우_세칸_이상은_이동할_수_없다(final PiecePosition destination) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThatThrownBy(() -> pawn.waypoints(destination))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "어떠한 경우에도 북쪽이 아닌 방향으로 이동할 수 없다. 예를 들어 [b2] 에서 [{0}] 으로는 이동이 불가능하다.")
        @CsvSource({
                "a2",
                "c2",
                "b1",
                "a1",
                "c1",
        })
        void 모든_경우_북쪽이_아닌_방향으로_이동할_수_없다(final PiecePosition destination) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
            final Pawn pawn = new Pawn(Color.WHITE, currentPosition);

            // when & then
            assertThatThrownBy(() -> pawn.waypoints(destination))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class 검정색_폰_움직임_테스트 {

        @ParameterizedTest(name = "움직이지 않은 경우 남쪽을 향해 직진 한칸, 두칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [b7] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
        @MethodSource("noneMovePawnDestinations")
        void 움직이지_않은_경우_남쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
            // given
            final PiecePosition currentPosition = PiecePosition.of("b7");
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThat(pawn.waypoints(destination).wayPoints())
                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
        }

        Stream<Arguments> noneMovePawnDestinations() {
            return Stream.of(
                    Arguments.of("b6", Named.of("b6", piecePositions("b6"))),
                    Arguments.of("b5", Named.of("b6, b5", piecePositions("b6", "b5"))),
                    Arguments.of("a6", Named.of("X", Collections.emptyList())),
                    Arguments.of("c6", Named.of("X", Collections.emptyList()))
            );
        }

        @ParameterizedTest(name = "움직인 경우 남쪽을 향해 직진 혹은 대각선으로 한 칸 이동이 가능하다. " +
                "예를 들어 [b5] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
        @MethodSource("movedPawnDestinations")
        void 움직인_경우_남쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);
            pawn.move(PiecePosition.of(5, 'b'));

            // when & then
            assertThat(pawn.waypoints(destination).wayPoints())
                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
        }

        Stream<Arguments> movedPawnDestinations() {
            return Stream.of(
                    Arguments.of("b4", Named.of("b4", piecePositions("b4"))),
                    Arguments.of("a4", Named.of("X", Collections.emptyList())),
                    Arguments.of("c4", Named.of("X", Collections.emptyList()))
            );
        }

        @ParameterizedTest(name = "어떠한 경우에도 세 칸 이상은 이동할 수 없다. 예를 들어 [b7] 에서 [{0}] 으로는 이동이 불가능하다. ")
        @CsvSource({
                "b4",
                "b3",
                "c5",
                "a5",
                "d6",
        })
        void 모든_경우_세칸_이상은_이동할_수_없다(final PiecePosition destination) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThatThrownBy(() -> pawn.waypoints(destination))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @ParameterizedTest(name = "어떠한 경우에도 남쪽이 아닌 방향으로 이동할 수 없다. 예를 들어 [b7] 에서 [{0}] 으로는 이동이 불가능하다.")
        @CsvSource({
                "b8",
                "a8",
                "c8",
                "a7",
                "c7",
        })
        void 모든_경우_남쪽이_아닌_방향으로_이동할_수_없다(final PiecePosition destination) {
            // given
            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
            final Pawn pawn = new Pawn(Color.BLACK, currentPosition);

            // when & then
            assertThatThrownBy(() -> pawn.waypoints(destination))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}

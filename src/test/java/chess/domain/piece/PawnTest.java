//package chess.domain.piece;
//
//import chess.domain.piece.position.PiecePosition;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.DisplayNameGeneration;
//import org.junit.jupiter.api.DisplayNameGenerator;
//import org.junit.jupiter.api.Named;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.MethodSource;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static chess.domain.piece.position.PiecePositionFixture.piecePositions;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@SuppressWarnings("NonAsciiCharacters")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayName("Pawn 은")
//class PawnTest {
//
//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    class 흰색_폰_움직임_테스트 {
//
//        @ParameterizedTest(name = "움직이지 않은 경우 북쪽을 향해 직진 한칸, 두칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
//                "예를 들어 [b2] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
//        @MethodSource("noneMovePawnDestinations")
//        void 움직이지_않은_경우_북쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
//            final Pawn pawn = new Pawn(Color.WHITE, currentPosition, new WhitePawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThat(pawn.waypoints(destination))
//                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
//        }
//
//        Stream<Arguments> noneMovePawnDestinations() {
//            return Stream.of(
//                    Arguments.of("b3", Named.of("X", Collections.emptyList())),
//                    Arguments.of("b4", Named.of("b3", piecePositions("b3"))),
//                    Arguments.of("a3", Named.of("X", Collections.emptyList())),
//                    Arguments.of("c3", Named.of("X", Collections.emptyList()))
//            );
//        }
//
//        @ParameterizedTest(name = "움직인 경우 북쪽을 향해 직진 혹은 대각선으로 한 칸 이동이 가능하다. " +
//                "예를 들어 [b2] 에서 [{0}]로 이동이 가능하다.")
//        @CsvSource({
//                "b5",
//                "a5",
//                "c5",
//        })
//        void 움직인_경우_북쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
//            final Pawn pawn = new Pawn(Color.WHITE, currentPosition, new WhitePawnMoveStrategy(), new InitialPawn());
//            final Piece next = pawn.move(PiecePosition.of(4, 'b'), null);
//
//            // when & then
//            assertThat(next.waypoints(destination)).isEmpty();
//        }
//
//        @Test
//        void 움직인_경우_북쪽으로_두칸_이동_불가() {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of("b2");
//            final PiecePosition firstDestination = PiecePosition.of("b3");
//            final PiecePosition secondDestination = PiecePosition.of("b5");
//            final Pawn pawn = new Pawn(Color.WHITE, currentPosition, new WhitePawnMoveStrategy(), new InitialPawn());
//            pawn.move(firstDestination, null);
//
//            // when & then
//            assertThatThrownBy(() -> pawn.move(secondDestination, null))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//
//        @ParameterizedTest(name = "어떠한 경우에도 세 칸 이상은 이동할 수 없다. 예를 들어 [b2] 에서 [{0}] 으로는 이동이 불가능하다. ")
//        @CsvSource({
//                "b5",
//                "b6",
//                "c4",
//                "a4",
//        })
//        void 모든_경우_세칸_이상은_이동할_수_없다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
//            final Pawn pawn = new Pawn(Color.WHITE, currentPosition, new WhitePawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThatThrownBy(() -> pawn.waypoints(destination))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//
//        @ParameterizedTest(name = "어떠한 경우에도 북쪽이 아닌 방향으로 이동할 수 없다. 예를 들어 [b2] 에서 [{0}] 으로는 이동이 불가능하다.")
//        @CsvSource({
//                "a2",
//                "c2",
//                "b1",
//                "a1",
//                "c1",
//        })
//        void 모든_경우_북쪽이_아닌_방향으로_이동할_수_없다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(2, 'b');
//            final Pawn pawn = new Pawn(Color.WHITE, currentPosition, new WhitePawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThatThrownBy(() -> pawn.waypoints(destination))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//    }
//
//    @Nested
//    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
//    class 검정색_폰_움직임_테스트 {
//
//        @ParameterizedTest(name = "움직이지 않은 경우 남쪽을 향해 직진 한칸, 두칸 혹은 대각선으로 한 칸 이동이 가능하다. " +
//                "예를 들어 [b7] 에서 [{0}]로 이동이 가능하며, 이때 다음 경로에 피스가 존재하면 안된다. [{1}]")
//        @MethodSource("noneMovePawnDestinations")
//        void 움직이지_않은_경우_남쪽으로_한칸_두칸_대각선한칸_이동_가능하다(final PiecePosition destination, final List<PiecePosition> expectedWaypoints) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of("b7");
//            final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThat(pawn.waypoints(destination))
//                    .containsExactlyInAnyOrderElementsOf(expectedWaypoints);
//        }
//
//        Stream<Arguments> noneMovePawnDestinations() {
//            return Stream.of(
//                    Arguments.of("b6", Named.of("X", Collections.emptyList())),
//                    Arguments.of("b5", Named.of("b6", piecePositions("b6"))),
//                    Arguments.of("a6", Named.of("X", Collections.emptyList())),
//                    Arguments.of("c6", Named.of("X", Collections.emptyList()))
//            );
//        }
//
//        @ParameterizedTest(name = "움직인 경우 남쪽을 향해 직진 혹은 대각선으로 한 칸 이동이 가능하다. " +
//                "예를 들어 [b5] 에서 [{0}]로 이동이 가능하다.")
//        @CsvSource({
//                "b4",
//                "a4",
//                "c4",
//        })
//        void 움직인_경우_남쪽으로_한칸_대각선한칸_이동_가능하다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
//            final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//            final Piece next = pawn.move(PiecePosition.of(5, 'b'), null);
//
//            // when & then
//            assertThat(next.waypoints(destination)).isEmpty();
//        }
//
//        @Test
//        void 움직인_경우_남쪽으로_두칸_이동_불가() {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of("b7");
//            final PiecePosition firstDestination = PiecePosition.of("b6");
//            final PiecePosition secondDestination = PiecePosition.of("b4");
//            final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//            pawn.move(firstDestination, null);
//
//            // when & then
//            assertThatThrownBy(() -> pawn.waypoints(secondDestination))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//
//        @ParameterizedTest(name = "어떠한 경우에도 세 칸 이상은 이동할 수 없다. 예를 들어 [b7] 에서 [{0}] 으로는 이동이 불가능하다. ")
//        @CsvSource({
//                "b4",
//                "b3",
//                "c5",
//                "a5",
//                "d6",
//        })
//        void 모든_경우_세칸_이상은_이동할_수_없다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
//            final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThatThrownBy(() -> pawn.waypoints(destination))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//
//        @ParameterizedTest(name = "어떠한 경우에도 남쪽이 아닌 방향으로 이동할 수 없다. 예를 들어 [b7] 에서 [{0}] 으로는 이동이 불가능하다.")
//        @CsvSource({
//                "b8",
//                "a8",
//                "c8",
//                "a7",
//                "c7",
//        })
//        void 모든_경우_남쪽이_아닌_방향으로_이동할_수_없다(final PiecePosition destination) {
//            // given
//            final PiecePosition currentPosition = PiecePosition.of(7, 'b');
//            final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//
//            // when & then
//            assertThatThrownBy(() -> pawn.waypoints(destination))
//                    .isInstanceOf(IllegalArgumentException.class);
//        }
//    }
//
//    @Test
//    void 단순_이동_시_직진만_가능하다() {
//        // given
//        final PiecePosition currentPosition = PiecePosition.of(7, 'b');
//        final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//
//        // when
//        final Piece next = pawn.move(PiecePosition.of(6, 'b'), null);
//
//        // then
//        assertThat(next.piecePosition()).isEqualTo(PiecePosition.of(6, 'b'));
//    }
//
//    @Test
//    void 단순_이동_시_대각선으로_이동하면_오류() {
//        // given
//        final PiecePosition currentPosition = PiecePosition.of(7, 'b');
//        final Pawn pawn = new Pawn(Color.BLACK, currentPosition, new BlackPawnMoveStrategy(), new InitialPawn());
//
//        // when & then
//        assertThatThrownBy(() -> pawn.move(PiecePosition.of(6, 'c'), null))
//                .isInstanceOf(IllegalArgumentException.class);
//        assertThatThrownBy(() -> pawn.move(PiecePosition.of(6, 'd'), null))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @Test
//    void 아군을_죽일_수_없다() {
//        // given
//        final Pawn pawn = new Pawn(Color.BLACK, PiecePosition.of("b6"), new BlackPawnMoveStrategy(), new InitialPawn());
//        final Pawn ally = new Pawn(Color.BLACK, PiecePosition.of("c5"), new BlackPawnMoveStrategy(), new InitialPawn());
//
//        // when & then
//        assertThatThrownBy(() -> pawn.move(ally.piecePosition, ally))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//
//    @Test
//    void 상대방이_대각선_위치에_있다면_죽일_수_있다() {
//        // given
//        final Pawn pawn = new Pawn(Color.BLACK, PiecePosition.of("b6"), new BlackPawnMoveStrategy(), new InitialPawn());
//        final Pawn enemy = new Pawn(Color.WHITE, PiecePosition.of("c5"), new BlackPawnMoveStrategy(), new InitialPawn());
//
//        // when
//        final Piece next = pawn.move(enemy.piecePosition, enemy);
//
//        // then
//        assertThat(next.piecePosition()).isEqualTo(enemy.piecePosition());
//    }
//
//    @Test
//    void 상대방이_대각선_위치에_없다면_죽일_수_없다() {
//        // given
//        final Pawn pawn = new Pawn(Color.BLACK, PiecePosition.of("b6"), new BlackPawnMoveStrategy(), new InitialPawn());
//        final Pawn enemy = new Pawn(Color.WHITE, PiecePosition.of("b5"), new BlackPawnMoveStrategy(), new InitialPawn());
//
//        // when & then
//        assertThatThrownBy(() -> pawn.move(enemy.piecePosition, enemy))
//                .isInstanceOf(IllegalArgumentException.class);
//    }
//}

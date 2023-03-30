package chess.domain.board;

import static chess.domain.piece.PieceType.WHITE_PAWN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardSnapShotTest {

    @Nested
    class canMove_메서드는 {

        @Nested
        class 나이트의_경우 {
            @Test
            void 이동경로로_이동할_수_있으면_true_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.B, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.THREE));

                assertThat(boardSnapShot.canMove(source, routes)).isTrue();
            }

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.G, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.E, Rank.TWO));

                assertThat(boardSnapShot.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 룩의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.A, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE));

                assertThat(boardSnapShot.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 비숍의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.C, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.D, Rank.TWO), new Square(File.E, Rank.THREE));

                assertThat(boardSnapShot.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 퀸의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.D, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.TWO), new Square(File.B, Rank.THREE));

                assertThat(boardSnapShot.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 킹의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final BoardSnapShot boardSnapShot = BoardFactory
                        .create()
                        .getBoardSnapShot();
                final Square source = new Square(File.E, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.F, Rank.TWO));

                assertThat(boardSnapShot.canMove(source, routes)).isFalse();
            }
        }
    }

    @Nested
    class canMovePawn_메서드는 {

        @Test
        void 폰이_초기_위치일_때_두칸_갈_수_있다() {
            final BoardSnapShot boardSnapShot = BoardFactory
                    .create()
                    .getBoardSnapShot();
            final Square source = new Square(File.A, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.A, Rank.FOUR));

            assertThat(boardSnapShot.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_한칸_갈_수_있다() {
            final BoardSnapShot boardSnapShot = BoardFactory
                    .create()
                    .getBoardSnapShot();
            final Square source = new Square(File.B, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.B, Rank.THREE));

            assertThat(boardSnapShot.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_대각선으로_갈_수_없다() {
            final BoardSnapShot boardSnapShot = BoardFactory
                    .create()
                    .getBoardSnapShot();
            final Square source = new Square(File.C, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.D, Rank.THREE));

            assertThat(boardSnapShot.canMovePawn(source, routes)).isFalse();
        }
    }

    @Test
    void 기물을_움직인다() {
        final Board board = BoardFactory.create();
        final Square source = new Square(File.E, Rank.TWO);
        final Square destination = new Square(File.E, Rank.FOUR);

        board.move(source, destination);

        assertAll(
                () -> assertThat(board.findPieceOf(source)).isEmpty(),
                () -> assertThat(board.findPieceOf(destination).get().getPieceType()).isEqualTo(WHITE_PAWN)
        );
    }
}

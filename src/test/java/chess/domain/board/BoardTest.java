package chess.domain.board;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardTest {

    @Test
    void 체스보드는_기물의_위치를_가지고_있다() {
        final Board board = BoardFactory.generate();

        assertAll(
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.ONE))).containsInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.EIGHT))).containsInstanceOf(Rook.class),

                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.ONE))).containsInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.EIGHT))).containsInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.ONE))).containsInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.EIGHT))).containsInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.ONE))).containsInstanceOf(Queen.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.EIGHT))).containsInstanceOf(Queen.class),

                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.ONE))).containsInstanceOf(King.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.EIGHT))).containsInstanceOf(King.class),

                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.ONE))).containsInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.EIGHT))).containsInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.ONE))).containsInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.EIGHT))).containsInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.ONE))).containsInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.TWO))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.SEVEN))).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.EIGHT))).containsInstanceOf(Rook.class)
        );
    }

    @Nested
    class canMove_메서드는 {

        @Nested
        class 나이트의_경우 {
            @Test
            void 이동경로로_이동할_수_있으면_true_반환한다() {
                final Board board = BoardFactory.generate();
                //Knight
                final Square source = new Square(File.B, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.THREE));

                assertThat(board.canMove(source, routes)).isTrue();
            }

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.generate();
                //Knight
                final Square source = new Square(File.G, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.E, Rank.TWO));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 룩의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.generate();
                //Rook
                final Square source = new Square(File.A, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 비숍의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.generate();
                //Bishop
                final Square source = new Square(File.C, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.D, Rank.TWO), new Square(File.E, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 퀸의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.generate();
                //Queen
                final Square source = new Square(File.D, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.TWO), new Square(File.B, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 킹의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.generate();
                //King
                final Square source = new Square(File.E, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.F, Rank.TWO));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }
    }

    @Nested
    class canMovePawn_메서드는 {

        @Test
        void 폰이_초기_위치일_때_두칸_갈_수_있다() {
            final Board board = BoardFactory.generate();
            final Square source = new Square(File.A, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.A, Rank.FOUR));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_한칸_갈_수_있다() {
            final Board board = BoardFactory.generate();
            final Square source = new Square(File.B, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.B, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_대각선으로_갈_수_없다() {
            final Board board = BoardFactory.generate();
            final Square source = new Square(File.C, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.D, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isFalse();
        }
    }

    @Test
    void 기물을_움직인다() {
        final Board board = BoardFactory.generate();
        final Square source = new Square(File.E, Rank.TWO);
        final Square destination = new Square(File.E, Rank.FOUR);

        board.move(source, destination);

        assertAll(
                () -> assertThat(board.findPieceOf(source)).isEmpty(),
                () -> assertThat(board.findPieceOf(destination)).containsInstanceOf(Pawn.class)
        );
    }

    @Test
    void 같은_파일의_폰이_2개인_경우의_점수를_계산한다() {
        final Board board = BoardFactory.generate();
        board.move(new Square(File.B, Rank.TWO), new Square(File.A, Rank.THREE));

        assertThat(board.resultOf(Color.WHITE)).isEqualTo(37);
    }

    @Test
    void 같은_파일의_폰이_3개인_경우의_점수를_계산한다() {
        final Board board = BoardFactory.generate();
        board.move(new Square(File.B, Rank.TWO), new Square(File.A, Rank.THREE));
        board.move(new Square(File.C, Rank.TWO), new Square(File.A, Rank.FOUR));

        assertThat(board.resultOf(Color.WHITE)).isEqualTo(36.5);
    }

    @Test
    void 게임이_종료되었는지_판단한다() {
        final Board board = BoardFactory.generate();
        final Square destination = new Square(File.E, Rank.ONE);
        final Square source = new Square(File.E, Rank.TWO);
        board.move(source, destination);

        assertThat(board.hasBothKing()).isFalse();
    }
}

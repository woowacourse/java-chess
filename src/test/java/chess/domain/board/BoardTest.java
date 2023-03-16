package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardTest {

    @Test
    void 체스보드는_기물의_위치를_가지고_있다() {
        final Board board = Board.generate();

        assertAll(
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.ONE))).isInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.A, Rank.EIGHT))).isInstanceOf(Rook.class),

                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.B, Rank.EIGHT))).isInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.C, Rank.EIGHT))).isInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.ONE))).isInstanceOf(Queen.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.D, Rank.EIGHT))).isInstanceOf(Queen.class),

                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.ONE))).isInstanceOf(King.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.E, Rank.EIGHT))).isInstanceOf(King.class),

                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.ONE))).isInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.F, Rank.EIGHT))).isInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.ONE))).isInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.G, Rank.EIGHT))).isInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.ONE))).isInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.TWO))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.SEVEN))).isInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(new Square(File.H, Rank.EIGHT))).isInstanceOf(Rook.class)
        );
    }

    @Nested
    class canMove_메서드는 {

        @Nested
        class 나이트의_경우 {
            @Test
            void 이동경로로_이동할_수_있으면_true_반환한다() {
                final Board board = Board.generate();
                //Knight
                final Square source = new Square(File.B, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.THREE));

                assertThat(board.canMove(source, routes)).isTrue();
            }

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = Board.generate();
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
                final Board board = Board.generate();
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
                final Board board = Board.generate();
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
                final Board board = Board.generate();
                //Bishop
                final Square source = new Square(File.D, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.TWO), new Square(File.B, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 킹의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = Board.generate();
                //Bishop
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
            final Board board = Board.generate();
            final Square source = new Square(File.A, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.A, Rank.FOUR));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_한칸_갈_수_있다() {
            final Board board = Board.generate();
            final Square source = new Square(File.B, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.B, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_대각선으로_갈_수_없다() {
            final Board board = Board.generate();
            final Square source = new Square(File.C, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.D, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isFalse();
        }
    }
}

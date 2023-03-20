package chess.domain.board;

import static chess.util.SquareFixture.A_EIGHT;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.A_SEVEN;
import static chess.util.SquareFixture.A_TWO;
import static chess.util.SquareFixture.B_EIGHT;
import static chess.util.SquareFixture.B_ONE;
import static chess.util.SquareFixture.B_SEVEN;
import static chess.util.SquareFixture.B_TWO;
import static chess.util.SquareFixture.C_EIGHT;
import static chess.util.SquareFixture.C_ONE;
import static chess.util.SquareFixture.C_SEVEN;
import static chess.util.SquareFixture.C_TWO;
import static chess.util.SquareFixture.D_EIGHT;
import static chess.util.SquareFixture.D_ONE;
import static chess.util.SquareFixture.D_SEVEN;
import static chess.util.SquareFixture.D_TWO;
import static chess.util.SquareFixture.E_EIGHT;
import static chess.util.SquareFixture.E_ONE;
import static chess.util.SquareFixture.E_SEVEN;
import static chess.util.SquareFixture.E_TWO;
import static chess.util.SquareFixture.F_EIGHT;
import static chess.util.SquareFixture.F_ONE;
import static chess.util.SquareFixture.F_SEVEN;
import static chess.util.SquareFixture.F_TWO;
import static chess.util.SquareFixture.G_EIGHT;
import static chess.util.SquareFixture.G_ONE;
import static chess.util.SquareFixture.G_SEVEN;
import static chess.util.SquareFixture.G_TWO;
import static chess.util.SquareFixture.H_EIGHT;
import static chess.util.SquareFixture.H_ONE;
import static chess.util.SquareFixture.H_SEVEN;
import static chess.util.SquareFixture.H_TWO;
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
        final Board board = BoardFactory.create();

        assertAll(
                () -> assertThat(board.findPieceOf(A_ONE)).containsInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(A_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(A_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(A_EIGHT)).containsInstanceOf(Rook.class),

                () -> assertThat(board.findPieceOf(B_ONE)).containsInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(B_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(B_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(B_EIGHT)).containsInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(C_ONE)).containsInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(C_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(C_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(C_EIGHT)).containsInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(D_ONE)).containsInstanceOf(Queen.class),
                () -> assertThat(board.findPieceOf(D_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(D_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(D_EIGHT)).containsInstanceOf(Queen.class),

                () -> assertThat(board.findPieceOf(E_ONE)).containsInstanceOf(King.class),
                () -> assertThat(board.findPieceOf(E_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(E_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(E_EIGHT)).containsInstanceOf(King.class),

                () -> assertThat(board.findPieceOf(F_ONE)).containsInstanceOf(Bishop.class),
                () -> assertThat(board.findPieceOf(F_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(F_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(F_EIGHT)).containsInstanceOf(Bishop.class),

                () -> assertThat(board.findPieceOf(G_ONE)).containsInstanceOf(Knight.class),
                () -> assertThat(board.findPieceOf(G_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(G_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(G_EIGHT)).containsInstanceOf(Knight.class),

                () -> assertThat(board.findPieceOf(H_ONE)).containsInstanceOf(Rook.class),
                () -> assertThat(board.findPieceOf(H_TWO)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(H_SEVEN)).containsInstanceOf(Pawn.class),
                () -> assertThat(board.findPieceOf(H_EIGHT)).containsInstanceOf(Rook.class)
        );
    }

    @Nested
    class canMove_메서드는 {

        @Nested
        class 나이트의_경우 {
            @Test
            void 이동경로로_이동할_수_있으면_true_반환한다() {
                final Board board = BoardFactory.create();
                final Square source = new Square(File.B, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.THREE));

                assertThat(board.canMove(source, routes)).isTrue();
            }

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.create();
                final Square source = new Square(File.G, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.E, Rank.TWO));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 룩의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.create();
                final Square source = new Square(File.A, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 비숍의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.create();
                final Square source = new Square(File.C, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.D, Rank.TWO), new Square(File.E, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 퀸의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.create();
                final Square source = new Square(File.D, Rank.ONE);
                final List<Square> routes = List.of(new Square(File.C, Rank.TWO), new Square(File.B, Rank.THREE));

                assertThat(board.canMove(source, routes)).isFalse();
            }
        }

        @Nested
        class 킹의_경우 {

            @Test
            void 이동경로로_이동할_수_없으면_false_반환한다() {
                final Board board = BoardFactory.create();
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
            final Board board = BoardFactory.create();
            final Square source = new Square(File.A, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.A, Rank.FOUR));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_한칸_갈_수_있다() {
            final Board board = BoardFactory.create();
            final Square source = new Square(File.B, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.B, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isTrue();
        }

        @Test
        void 폰이_초기_위치일_때_대각선으로_갈_수_없다() {
            final Board board = BoardFactory.create();
            final Square source = new Square(File.C, Rank.TWO);
            final List<Square> routes = List.of(new Square(File.D, Rank.THREE));

            assertThat(board.canMovePawn(source, routes)).isFalse();
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
                () -> assertThat(board.findPieceOf(destination)).containsInstanceOf(Pawn.class)
        );
    }
}

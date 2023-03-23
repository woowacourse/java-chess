package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.BLACK_PAWN;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;
import static chess.domain.piece.PieceType.WHITE_PAWN;
import static chess.util.SquareFixture.A_EIGHT;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.A_SEVEN;
import static chess.util.SquareFixture.A_TWO;
import static chess.util.SquareFixture.B_EIGHT;
import static chess.util.SquareFixture.B_ONE;
import static chess.util.SquareFixture.B_SEVEN;
import static chess.util.SquareFixture.B_THREE;
import static chess.util.SquareFixture.B_TWO;
import static chess.util.SquareFixture.C_EIGHT;
import static chess.util.SquareFixture.C_ONE;
import static chess.util.SquareFixture.C_SEVEN;
import static chess.util.SquareFixture.C_THREE;
import static chess.util.SquareFixture.C_TWO;
import static chess.util.SquareFixture.D_EIGHT;
import static chess.util.SquareFixture.D_FOUR;
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

import chess.domain.piece.Piece;
import java.util.Map;
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
                () -> assertThat(board.findPieceOf(A_ONE).get().getPieceType()).isEqualTo(ROOK),
                () -> assertThat(board.findPieceOf(A_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(A_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(A_EIGHT).get().getPieceType()).isEqualTo(ROOK),

                () -> assertThat(board.findPieceOf(B_ONE).get().getPieceType()).isEqualTo(KNIGHT),
                () -> assertThat(board.findPieceOf(B_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(B_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(B_EIGHT).get().getPieceType()).isEqualTo(KNIGHT),

                () -> assertThat(board.findPieceOf(C_ONE).get().getPieceType()).isEqualTo(BISHOP),
                () -> assertThat(board.findPieceOf(C_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(C_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(C_EIGHT).get().getPieceType()).isEqualTo(BISHOP),

                () -> assertThat(board.findPieceOf(D_ONE).get().getPieceType()).isEqualTo(QUEEN),
                () -> assertThat(board.findPieceOf(D_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(D_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(D_EIGHT).get().getPieceType()).isEqualTo(QUEEN),

                () -> assertThat(board.findPieceOf(E_ONE).get().getPieceType()).isEqualTo(KING),
                () -> assertThat(board.findPieceOf(E_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(E_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(E_EIGHT).get().getPieceType()).isEqualTo(KING),

                () -> assertThat(board.findPieceOf(F_ONE).get().getPieceType()).isEqualTo(BISHOP),
                () -> assertThat(board.findPieceOf(F_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(F_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(F_EIGHT).get().getPieceType()).isEqualTo(BISHOP),

                () -> assertThat(board.findPieceOf(G_ONE).get().getPieceType()).isEqualTo(KNIGHT),
                () -> assertThat(board.findPieceOf(G_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(G_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(G_EIGHT).get().getPieceType()).isEqualTo(KNIGHT),

                () -> assertThat(board.findPieceOf(H_ONE).get().getPieceType()).isEqualTo(ROOK),
                () -> assertThat(board.findPieceOf(H_TWO).get().getPieceType()).isEqualTo(WHITE_PAWN),
                () -> assertThat(board.findPieceOf(H_SEVEN).get().getPieceType()).isEqualTo(BLACK_PAWN),
                () -> assertThat(board.findPieceOf(H_EIGHT).get().getPieceType()).isEqualTo(ROOK)
        );
    }

    @Test
    void 기물을_움직인다() {
        final Board board = BoardFactory.create();

        board.move(B_TWO, B_THREE);

        assertThat(board.findPieceOf(B_THREE).get().getPieceType()).isEqualTo(WHITE_PAWN);
    }

    @Nested
    class calculateScoreOfColor_메서드는 {

        @Test
        void 같은_색의_폰이_일직선에_있을경우_점수가_감소되서_계산된다() {
            final Map<Square, Piece> table = Map.ofEntries(
                    Map.entry(A_ONE, new Piece(BLACK, BLACK_PAWN)),
                    Map.entry(A_TWO, new Piece(BLACK, BLACK_PAWN)),
                    Map.entry(B_TWO, new Piece(BLACK, BLACK_PAWN)),
                    Map.entry(B_THREE, new Piece(BLACK, ROOK)),
                    Map.entry(C_THREE, new Piece(BLACK, KNIGHT)),
                    Map.entry(D_FOUR, new Piece(BLACK, BISHOP)),
                    Map.entry(E_SEVEN, new Piece(BLACK, QUEEN)),
                    Map.entry(F_EIGHT, new Piece(BLACK, KING)),
                    Map.entry(G_ONE, new Piece(WHITE, QUEEN))
            );
            final Board board = new Board(table);

            assertThat(board.calculateScoreOfColor(BLACK)).isEqualTo(21.5);
        }

        @Test
        void 같은_색의_폰이_일직선에_없을경우_점수가_감소되지_않은채_계산된다() {
            final Map<Square, Piece> table = Map.ofEntries(
                    Map.entry(A_ONE, new Piece(BLACK, BLACK_PAWN)),
                    Map.entry(B_TWO, new Piece(BLACK, BLACK_PAWN)),
                    Map.entry(B_THREE, new Piece(BLACK, ROOK)),
                    Map.entry(C_THREE, new Piece(BLACK, KNIGHT)),
                    Map.entry(D_FOUR, new Piece(BLACK, BISHOP)),
                    Map.entry(E_SEVEN, new Piece(BLACK, QUEEN)),
                    Map.entry(F_EIGHT, new Piece(BLACK, KING)),
                    Map.entry(G_ONE, new Piece(WHITE, QUEEN))
            );
            final Board board = new Board(table);

            assertThat(board.calculateScoreOfColor(BLACK)).isEqualTo(21.5);
        }
    }
}

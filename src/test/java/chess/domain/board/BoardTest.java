package chess.domain.board;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
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

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
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

    @Test
    void 기물을_움직인다() {
        final Board board = BoardFactory.create();

        board.move(B_TWO, B_THREE);

        assertThat(board.findPieceOf(B_THREE)).containsInstanceOf(Pawn.class);
    }

    @Nested
    class calculateScoreOfColor_메서드는 {

        @Test
        void 같은_색의_폰이_일직선에_있을경우_점수가_감소되서_계산된다() {
            final Map<Square, Piece> table = Map.ofEntries(
                    Map.entry(A_ONE, new Pawn(BLACK)),
                    Map.entry(A_TWO, new Pawn(BLACK)),
                    Map.entry(B_TWO, new Pawn(BLACK)),
                    Map.entry(B_THREE, new Rook(BLACK)),
                    Map.entry(C_THREE, new Knight(BLACK)),
                    Map.entry(D_FOUR, new Bishop(BLACK)),
                    Map.entry(E_SEVEN, new Queen(BLACK)),
                    Map.entry(F_EIGHT, new King(BLACK)),
                    Map.entry(G_ONE, new Queen(WHITE))
            );
            final Board board = new Board(table);

            assertThat(board.calculateScoreOfColor(BLACK)).isEqualTo(21.5);
        }

        @Test
        void 같은_색의_폰이_일직선에_없을경우_점수가_감소되지_않은채_계산된다() {
            final Map<Square, Piece> table = Map.ofEntries(
                    Map.entry(A_ONE, new Pawn(BLACK)),
                    Map.entry(B_TWO, new Pawn(BLACK)),
                    Map.entry(B_THREE, new Rook(BLACK)),
                    Map.entry(C_THREE, new Knight(BLACK)),
                    Map.entry(D_FOUR, new Bishop(BLACK)),
                    Map.entry(E_SEVEN, new Queen(BLACK)),
                    Map.entry(F_EIGHT, new King(BLACK)),
                    Map.entry(G_ONE, new Queen(WHITE))
            );
            final Board board = new Board(table);

            assertThat(board.calculateScoreOfColor(BLACK)).isEqualTo(21.5);
        }
    }
}

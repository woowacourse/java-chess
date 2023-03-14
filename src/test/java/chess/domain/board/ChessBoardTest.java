package chess.domain.board;

import chess.domain.board.position.PiecePosition;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Staunton;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("ChessBoard 은")
class ChessBoardTest {

    /**
     * RNBQKBNR  8 (rank 8)
     * PPPPPPPP  7
     * ........  6
     * ........  5
     * ........  4
     * ........  3
     * pppppppp  2
     * rnbqkbnr  1 (rank 1)
     * <p>
     * abcdefgh
     */
    @Test
    void 기본_체스_규칙에_맞게_생성된다() {
        // when
        final ChessBoard chessBoard = ChessBoard.create();

        // then
        assertPiece(chessBoard, 1, 'a', Color.WHITE, Staunton.ROOK);
        assertPiece(chessBoard, 1, 'b', Color.WHITE, Staunton.KNIGHT);
        assertPiece(chessBoard, 1, 'c', Color.WHITE, Staunton.BISHOP);
        assertPiece(chessBoard, 1, 'd', Color.WHITE, Staunton.QUEEN);
        assertPiece(chessBoard, 1, 'e', Color.WHITE, Staunton.KING);
        assertPiece(chessBoard, 1, 'f', Color.WHITE, Staunton.BISHOP);
        assertPiece(chessBoard, 1, 'g', Color.WHITE, Staunton.KNIGHT);
        assertPiece(chessBoard, 1, 'h', Color.WHITE, Staunton.ROOK);
        assertPiece(chessBoard, 8, 'a', Color.BLACK, Staunton.ROOK);
        assertPiece(chessBoard, 8, 'b', Color.BLACK, Staunton.KNIGHT);
        assertPiece(chessBoard, 8, 'c', Color.BLACK, Staunton.BISHOP);
        assertPiece(chessBoard, 8, 'd', Color.BLACK, Staunton.QUEEN);
        assertPiece(chessBoard, 8, 'e', Color.BLACK, Staunton.KING);
        assertPiece(chessBoard, 8, 'f', Color.BLACK, Staunton.BISHOP);
        assertPiece(chessBoard, 8, 'g', Color.BLACK, Staunton.KNIGHT);
        assertPiece(chessBoard, 8, 'h', Color.BLACK, Staunton.ROOK);
        for (char file = 'a'; file <= 'h'; file++) {
            assertPiece(chessBoard, 2, file, Color.WHITE, Staunton.PAWN);
            assertPiece(chessBoard, 7, file, Color.BLACK, Staunton.PAWN);
        }
    }

    private static void assertPiece(final ChessBoard chessBoard, final int rank, final char file, final Color color, final Staunton staunton) {
        Piece piece = chessBoard.get(PiecePosition.of(rank, file));
        assertThat(piece.color()).isEqualTo(color);
        assertThat(piece.staunton()).isEqualTo(staunton);
    }
}

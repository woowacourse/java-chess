package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.type.Bishop;
import chess.domain.piece.type.King;
import chess.domain.piece.type.Knight;
import chess.domain.piece.type.Pawn;
import chess.domain.piece.type.Queen;
import chess.domain.piece.type.Rook;
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
        assertPiece(chessBoard, 1, 'a', Color.WHITE, Rook.class);
        assertPiece(chessBoard, 1, 'b', Color.WHITE, Knight.class);
        assertPiece(chessBoard, 1, 'c', Color.WHITE, Bishop.class);
        assertPiece(chessBoard, 1, 'd', Color.WHITE, Queen.class);
        assertPiece(chessBoard, 1, 'e', Color.WHITE, King.class);
        assertPiece(chessBoard, 1, 'f', Color.WHITE, Bishop.class);
        assertPiece(chessBoard, 1, 'g', Color.WHITE, Knight.class);
        assertPiece(chessBoard, 1, 'h', Color.WHITE, Rook.class);
        assertPiece(chessBoard, 8, 'a', Color.BLACK, Rook.class);
        assertPiece(chessBoard, 8, 'b', Color.BLACK, Knight.class);
        assertPiece(chessBoard, 8, 'c', Color.BLACK, Bishop.class);
        assertPiece(chessBoard, 8, 'd', Color.BLACK, Queen.class);
        assertPiece(chessBoard, 8, 'e', Color.BLACK, King.class);
        assertPiece(chessBoard, 8, 'f', Color.BLACK, Bishop.class);
        assertPiece(chessBoard, 8, 'g', Color.BLACK, Knight.class);
        assertPiece(chessBoard, 8, 'h', Color.BLACK, Rook.class);
        for (char file = 'a'; file <= 'h'; file++) {
            assertPiece(chessBoard, 2, file, Color.WHITE, Pawn.class);
            assertPiece(chessBoard, 7, file, Color.BLACK, Pawn.class);
        }
    }

    private static void assertPiece(final ChessBoard chessBoard, final int rank, final char file, final Color color, final Class<?> type) {
        final Piece piece = chessBoard.get(PiecePosition.of(rank, file));
        assertThat(piece.color()).isEqualTo(color);
        assertThat(piece).isExactlyInstanceOf(type);
    }
}

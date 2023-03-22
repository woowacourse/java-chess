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

import java.util.Map;
import java.util.function.Function;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.position.PiecePosition.of;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // then
        assertPiece(chessBoard, 1, 'a', WHITE, Rook.class);
        assertPiece(chessBoard, 1, 'b', WHITE, Knight.class);
        assertPiece(chessBoard, 1, 'c', WHITE, Bishop.class);
        assertPiece(chessBoard, 1, 'd', WHITE, Queen.class);
        assertPiece(chessBoard, 1, 'e', WHITE, King.class);
        assertPiece(chessBoard, 1, 'f', WHITE, Bishop.class);
        assertPiece(chessBoard, 1, 'g', WHITE, Knight.class);
        assertPiece(chessBoard, 1, 'h', WHITE, Rook.class);
        assertPiece(chessBoard, 8, 'a', Color.BLACK, Rook.class);
        assertPiece(chessBoard, 8, 'b', Color.BLACK, Knight.class);
        assertPiece(chessBoard, 8, 'c', Color.BLACK, Bishop.class);
        assertPiece(chessBoard, 8, 'd', Color.BLACK, Queen.class);
        assertPiece(chessBoard, 8, 'e', Color.BLACK, King.class);
        assertPiece(chessBoard, 8, 'f', Color.BLACK, Bishop.class);
        assertPiece(chessBoard, 8, 'g', Color.BLACK, Knight.class);
        assertPiece(chessBoard, 8, 'h', Color.BLACK, Rook.class);
        for (char file = 'a'; file <= 'h'; file++) {
            assertPiece(chessBoard, 2, file, WHITE, Pawn.class);
            assertPiece(chessBoard, 7, file, Color.BLACK, Pawn.class);
        }
    }

    private static void assertPiece(final ChessBoard chessBoard, final int rank, final char file, final Color color, final Class<?> type) {
        final Piece piece = chessBoard.get(of(file, rank));
        assertThat(piece.color()).isEqualTo(color);
        assertThat(piece).isExactlyInstanceOf(type);
    }

    @Test
    void 위치를_받아_주어진_위치의_말을_이동시킨다() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when
        chessBoard.movePiece(new Turn(WHITE), of('b', 2), of('b', 3));

        // then
        final Map<PiecePosition, Piece> pieceMap = chessBoard.pieces()
                .stream()
                .collect(toMap(Piece::piecePosition, Function.identity()));
        assertThat(pieceMap.get(of('b', 2))).isNull();
        assertThat(pieceMap.get(of('b', 3))).isInstanceOf(Pawn.class);
    }

    @Test
    void 상대_말을_움직이면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of('b', 2), of('b', 3))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 말이_없으면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), of('b', 5), of('b', 3))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_없는_위치면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of('b', 7), of('b', 4))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_있으나_경로에_말이_존재하면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of('d', 8), of('b', 6))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_있으나_도착지에_아군_말이_존재하면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of('d', 8), of('c', 7))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_일직선으로_움직이는_경우_적군_말이_있으면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 2), PiecePosition.of('b', 4));
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of('b', 7), PiecePosition.of('b', 5));

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 4), PiecePosition.of('b', 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_대각선으로_움직이는_경우_적군_말이_없으면_오류() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 2), PiecePosition.of('b', 4));

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 4), PiecePosition.of('c', 5))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_대각선으로_움직이려면_적군_말이_필요하다() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 2), PiecePosition.of('b', 4));
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of('c', 7), PiecePosition.of('c', 5));

        // when
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('b', 4), PiecePosition.of('c', 5));

        // then
        assertThat(chessBoard.pieces().size()).isEqualTo(31);
    }

    @Test
    void 적군_말을_잡으면_해당_말은_사라진다() {
        // given
        final ChessBoard chessBoard = ChessBoardFactory.create();
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('e', 2), PiecePosition.of('e', 4));
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('d', 1), PiecePosition.of('h', 5));

        // when
        assertThat(chessBoard.pieces().size()).isEqualTo(32);
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of('h', 5), PiecePosition.of('f', 7));

        // then
        assertThat(chessBoard.pieces().size()).isEqualTo(31);
    }
}

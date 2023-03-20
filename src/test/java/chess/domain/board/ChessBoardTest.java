package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.strategy.BishopMovement;
import chess.domain.piece.strategy.KingMovement;
import chess.domain.piece.strategy.KnightMovement;
import chess.domain.piece.strategy.QueenMovement;
import chess.domain.piece.strategy.RookMovement;
import chess.domain.piece.strategy.pawn.PawnMovement;
import org.junit.jupiter.api.BeforeEach;
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
    private ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = new ChessBoardFactory().create();
    }

    @Test
    void 기본_체스_규칙에_맞게_생성된다() {
        // then
        assertPiece(chessBoard, 1, 'a', WHITE, RookMovement.class);
        assertPiece(chessBoard, 1, 'b', WHITE, KnightMovement.class);
        assertPiece(chessBoard, 1, 'c', WHITE, BishopMovement.class);
        assertPiece(chessBoard, 1, 'd', WHITE, QueenMovement.class);
        assertPiece(chessBoard, 1, 'e', WHITE, KingMovement.class);
        assertPiece(chessBoard, 1, 'f', WHITE, BishopMovement.class);
        assertPiece(chessBoard, 1, 'g', WHITE, KnightMovement.class);
        assertPiece(chessBoard, 1, 'h', WHITE, RookMovement.class);
        assertPiece(chessBoard, 8, 'a', Color.BLACK, RookMovement.class);
        assertPiece(chessBoard, 8, 'b', Color.BLACK, KnightMovement.class);
        assertPiece(chessBoard, 8, 'c', Color.BLACK, BishopMovement.class);
        assertPiece(chessBoard, 8, 'd', Color.BLACK, QueenMovement.class);
        assertPiece(chessBoard, 8, 'e', Color.BLACK, KingMovement.class);
        assertPiece(chessBoard, 8, 'f', Color.BLACK, BishopMovement.class);
        assertPiece(chessBoard, 8, 'g', Color.BLACK, KnightMovement.class);
        assertPiece(chessBoard, 8, 'h', Color.BLACK, RookMovement.class);
        for (char file = 'a'; file <= 'h'; file++) {
            assertPiece(chessBoard, 2, file, WHITE, PawnMovement.class);
            assertPiece(chessBoard, 7, file, Color.BLACK, PawnMovement.class);
        }
    }

    private static void assertPiece(final ChessBoard chessBoard, final int rank, final char file, final Color color, final Class<?> type) {
        final Piece piece = chessBoard.findByPosition(of(rank, file));
        assertThat(piece.color()).isEqualTo(color);
        assertThat(piece.pieceMovement()).isInstanceOf(type);
    }

    @Test
    void 위치를_받아_주어진_위치의_말을_이동시킨다() {
        // when
        chessBoard.movePiece(new Turn(WHITE), of(2, 'b'), of(3, 'b'));

        // then
        final Map<PiecePosition, Piece> pieceMap = chessBoard.pieces()
                .stream()
                .collect(toMap(Piece::piecePosition, Function.identity()));
        assertThat(pieceMap.get(of(2, 'b'))).isNull();
        assertThat(pieceMap.get(of(3, 'b')).pieceMovement()).isInstanceOf(PawnMovement.class);
    }

    @Test
    void 상대_말을_움직이면_오류() {
        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of(2, 'b'), of(3, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 말이_없으면_오류() {
        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), of(5, 'b'), of(3, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_없는_위치면_오류() {
        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of(7, 'b'), of(4, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_있으나_경로에_말이_존재하면_오류() {
        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of(8, 'd'), of(6, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 움직일_수_있으나_도착지에_아군_말이_존재하면_오류() {
        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(BLACK), of(8, 'd'), of(7, 'c'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_일직선으로_한칸_움직이는_경우_적군_말이_있으면_오류() {
        // given
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(2, 'b'), PiecePosition.of(4, 'b'));
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of(7, 'b'), PiecePosition.of(5, 'b'));

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(4, 'b'), PiecePosition.of(5, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_일직선으로_두칸_움직이는_경우_적군_말이_있으면_오류() {
        // given
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of(7, 'b'), PiecePosition.of(5, 'b'));
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of(5, 'b'), PiecePosition.of(4, 'b'));

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(2, 'b'), PiecePosition.of(4, 'b'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_대각선으로_움직이는_경우_적군_말이_없으면_오류() {
        // given
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(2, 'b'), PiecePosition.of(4, 'b'));

        // when & then
        assertThatThrownBy(() ->
                chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(4, 'b'), PiecePosition.of(5, 'c'))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 폰이_대각선으로_움직이려면_적군_말이_필요하다() {
        // given
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(2, 'b'), PiecePosition.of(4, 'b'));
        chessBoard.movePiece(new Turn(BLACK), PiecePosition.of(7, 'c'), PiecePosition.of(5, 'c'));

        // when
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(4, 'b'), PiecePosition.of(5, 'c'));

        // then
        assertThat(chessBoard.pieces().size()).isEqualTo(31);
    }

    @Test
    void 적군_말을_잡으면_해당_말은_사라진다() {
        // given
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(2, 'e'), PiecePosition.of(4, 'e'));
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(1, 'd'), PiecePosition.of(5, 'h'));

        // when
        assertThat(chessBoard.pieces().size()).isEqualTo(32);
        chessBoard.movePiece(new Turn(WHITE), PiecePosition.of(5, 'h'), PiecePosition.of(7, 'f'));

        // then
        assertThat(chessBoard.pieces().size()).isEqualTo(31);
    }
}

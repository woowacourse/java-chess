package chess.domain;

import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A4;
import static chess.domain.PositionFixture.A5;
import static chess.domain.PositionFixture.A7;
import static chess.domain.PositionFixture.B2;
import static chess.domain.PositionFixture.B4;
import static chess.domain.PositionFixture.C1;
import static chess.domain.PositionFixture.D1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.game.Board;
import chess.domain.game.exception.ChessGameException;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BoardTest {

    @Test
    void 체스판_초기_점수_테스트() {
        //given
        Board board = new Board();

        //when
        Map<Color, Double> boardScore = board.getStatus();

        //then
        assertThat(boardScore)
                .containsEntry(Color.BLACK, 38d)
                .containsEntry(Color.WHITE, 38d);
    }

    @Test
    void 첫_번째_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPieces().get(0);

        //then
        var check = result.stream()
                .map(Piece::getType)
                .collect(Collectors.toList());
        assertThat(check)
                .containsExactly(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                        PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK)
                .hasSize(8);
    }

    @Test
    void 두_번째_줄_테스트() {
        //given
        Board board = new Board();

        //when
        var result = board.getPieces().get(1);

        //then
        var check = result.stream()
                .map(Piece::getType)
                .collect(Collectors.toList());
        assertThat(check)
                .containsOnly(PieceType.PAWN)
                .hasSize(8);
    }

    @Test
    void 같은편_말이_있는_곳으로_이동할_수_없다() {
        //given
        Board board = new Board();

        //expect
        assertThatThrownBy(() -> board.movePiece(C1, A3))
                .isInstanceOf(ChessGameException.class);
    }

    @Test
    void 같은편_말이_없는_곳으로_이동할_수_있다() {
        //given
        Board board = new Board();

        assertDoesNotThrow(() -> {
            board.movePiece(B2, B4);
            board.movePiece(A7, A5);
            board.movePiece(C1, A3);
        });
    }

    @Test
    void 같은편_말이_없는_곳으로_이동할_수_있다2() {
        //given
        Board board = new Board();

        assertDoesNotThrow(() -> {
            board.movePiece(B2, B4);
            board.movePiece(A7, A5);
            board.movePiece(C1, A3);
            board.movePiece(A5, A4);
            board.movePiece(D1, C1);
        });
    }

    @Test
    void 빈_말은_이동할_수_없다() {
        //given
        Board board = new Board();

        //expect
        assertThatThrownBy(() -> board.movePiece(A3, A4))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("이동할 말이 없습니다.");
    }

    @Test
    void 같은_색_말을_연속해서_움직일_수_없다() {
        //given
        Board board = new Board();
        board.movePiece(B2, B4);

        //expect
        assertThatThrownBy(() -> board.movePiece(A2, A4))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("상대 말을 움직일 수 없습니다.");
    }
}

package chess;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스판은 32개의 체스 기물을 생성한다.")
    @Test
    void chess_board_create_pieces() {
        //given
        //when
        ChessBoard chessBoard = new ChessBoard();

        //then
        assertThat(chessBoard.getValue()).hasSize(32);
    }

    @DisplayName("처음 초기화된 체스판의 A1에는 Rook이 있다.")
    @Test
    void check_rook_a1() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.ONE)))
                .isInstanceOf(Rook.class);
    }

    @DisplayName("처음 초기화된 체스판의 H1에는 Rook이 있다.")
    @Test
    void check_rook_h1() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.ONE)))
                .isInstanceOf(Rook.class);
    }

    @DisplayName("처음 초기화된 체스판의 A8에는 Rook이 있다.")
    @Test
    void check_rook_A8() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(new ChessBoardPosition(ChessBoardColumn.A, ChessBoardRow.EIGHT)))
                .isInstanceOf(Rook.class);
    }

    @DisplayName("처음 초기화된 체스판의 H8에는 Rook이 있다.")
    @Test
    void check_rook_h8() {
        //given
        ChessBoard chessBoard = new ChessBoard();
        //when
        Map<ChessBoardPosition, Piece> boardValue = chessBoard.getValue();
        //then
        assertThat(boardValue.get(new ChessBoardPosition(ChessBoardColumn.H, ChessBoardRow.EIGHT)))
                .isInstanceOf(Rook.class);
    }
}

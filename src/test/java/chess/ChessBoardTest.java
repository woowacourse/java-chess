package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스판은 8 x 8 크기를 가진다.")
    @Test
    public void chessBoard() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        int size = chessBoard.getSize();

        //then
        assertThat(size).isEqualTo(64);
    }

    @DisplayName("체스판 초기 세팅 테스트")
    @Test
    public void init() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        int size = chessBoard.init();

        //then
        assertThat(size).isEqualTo(32);
    }

}

package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {
    @DisplayName("체스판 초기 세팅 테스트")
    @Test
    public void init() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        int size = chessBoard.countPieces();

        //then
        assertThat(size).isEqualTo(32);
    }
}

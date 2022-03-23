package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @DisplayName("체스판을 초기화 시킨다.")
    @Test
    void chess_board_initialize_position() {
        //given
        ChessGame chessGame = new ChessGame();
        //when
        //then
        assertThat(chessGame.initializeBoard()).isInstanceOf(ChessBoard.class);
    }
}

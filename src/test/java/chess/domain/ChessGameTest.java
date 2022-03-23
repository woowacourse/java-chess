package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
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
        assertThat(chessGame.initializeBoard()).isInstanceOf(Board.class);
    }
}

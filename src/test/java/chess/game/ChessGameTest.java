package chess.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    void START_커맨드를_입력받으면_현재_진행상태는_true를_반환한다() {
        // given
        ChessGame chessGame = new ChessGame();

        // when
        chessGame.receiveCommand(Command.START);

        // then
        assertThat(chessGame.isProcessing()).isTrue();
    }

    @Test
    void END_커맨드를_입력받으면_현재_진행상태는_false를_반환한다() {
        // given
        ChessGame chessGame = new ChessGame();

        // when
        chessGame.receiveCommand(Command.END);

        // then
        assertThat(chessGame.isProcessing()).isFalse();
    }
}

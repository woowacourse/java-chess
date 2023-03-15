package chess.game;

import chess.board.ChessBoard;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    @Test
    void 체스게임은_게임을_시작한다() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        ChessBoard chessBoard = chessGame.receiveCommand(Command.START);

        //then
        assertThat(chessBoard).isNotNull();
    }

    @Test
    void 체스게임은_게임을_종료한다() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        ChessBoard chessBoard = chessGame.receiveCommand(Command.END);

        //then
        assertThat(chessBoard).isNull();
    }
}

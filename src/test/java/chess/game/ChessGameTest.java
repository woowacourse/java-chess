package chess.game;

import chess.board.ChessBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Nested
    class 체스_게임은_ {
        @Test
        void 시작_커맨드_입력_시_진행중인_상태다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

            //when
            chessGame.receiveCommand(Command.START);

            //then
            Assertions.assertThat(chessGame.isEnd()).isFalse();
        }

        @Test
        void 생성_시_진행중인_상태가_아니다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

            //when & then
            Assertions.assertThat(chessGame.isEnd()).isTrue();
        }

        @Test
        void 종료_커맨드_입력_시_진행중인_상태가_아니다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

            //when
            chessGame.receiveCommand(Command.START);
            chessGame.receiveCommand(Command.END);

            //then
            Assertions.assertThat(chessGame.isEnd()).isTrue();
        }
    }
}

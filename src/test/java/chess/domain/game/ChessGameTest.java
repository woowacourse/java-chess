package chess.domain.game;

import chess.domain.board.ChessBoard;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
            assertThat(chessGame.isEnd()).isFalse();
        }

        @Test
        void 생성_시_진행중인_상태가_아니다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

            //when & then
            assertThat(chessGame.isEnd()).isTrue();
        }

        @Test
        void 종료_커맨드_입력_시_진행중인_상태가_아니다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

            //when
            chessGame.receiveCommand(Command.START);
            chessGame.receiveCommand(Command.END);

            //then
            assertThat(chessGame.isEnd()).isTrue();
        }
    }

    @Test
    void 말을_움직일_수_있다() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

        //when & then
        assertDoesNotThrow(() -> chessGame.movePiece(A2, A4));
    }

    @Test
    void 체스판을_생성한다() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard());

        //when & then
        assertThat(chessGame.getChessBoard()).isNotNull();
    }
}

package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static chess.domain.game.GameStatus.GAME_OVER;
import static chess.domain.game.GameStatus.IDLE;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.D4;
import static chess.fixture.PositionFixture.D5;
import static chess.fixture.PositionFixture.D7;
import static chess.fixture.PositionFixture.E1;
import static chess.fixture.PositionFixture.E2;
import static chess.fixture.PositionFixture.E3;
import static chess.fixture.PositionFixture.E4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    public static final int INIT_SCORE = 38;

    @Nested
    class 체스_게임은_ {
        @Test
        void 시작_커맨드_입력_시_진행중인_상태다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.IDLE);

            //when
            chessGame.receiveCommand(Command.START);

            //then
            assertThat(chessGame.isEnd()).isFalse();
        }

        @Test
        void 종료_커맨드_입력_시_진행중인_상태가_아니다() {
            //given
            ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.IDLE);

            //when
            chessGame.receiveCommand(Command.START);
            chessGame.receiveCommand(Command.END);

            //then
            assertThat(chessGame.getStatus()).isEqualTo(IDLE);
        }
    }

    @Test
    void 말을_움직일_수_있다() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.PLAYING);

        //when & then
        assertDoesNotThrow(() -> chessGame.movePiece(A2, A4));
    }

    @Test
    void 체스판을_생성한다() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.IDLE);

        //when & then
        assertThat(chessGame.getChessBoard()).isNotNull();
    }


    /*
        RNBQKBNR
        PPP.PPPP
        ........
        ........
        ....p...
        ....P...
        pppp.ppp
        rnbq.bnr
     */
    @Test
    void 왕이_죽으면_게임오버() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.PLAYING);

        //when
        chessGame.movePiece(E2, E4);
        chessGame.movePiece(D7, D5);
        chessGame.movePiece(E1, E2);
        chessGame.movePiece(D5, D4);
        chessGame.movePiece(E2, E3);
        chessGame.movePiece(D4, E3);

        //then
        assertThat(chessGame.getStatus()).isEqualTo(GAME_OVER);
    }

    @Test
    void 게임오버가_된_상태에서_move_호출_시_예외() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.PLAYING);

        //when
        chessGame.movePiece(E2, E4);
        chessGame.movePiece(D7, D5);
        chessGame.movePiece(E1, E2);
        chessGame.movePiece(D5, D4);
        chessGame.movePiece(E2, E3);
        chessGame.movePiece(D4, E3);

        //then
        assertThatThrownBy(() -> chessGame.movePiece(D4, E3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 종료된 게임입니다.");
    }

    @Test
    void 주어진_팀의_점수를_구할_수_있다() {
        //given
        ChessGame chessGame = new ChessGame(ChessBoard.createBoard(), new Turn(), GameStatus.PLAYING);

        //when & then
        assertThat(chessGame.calculateScore(Team.WHITE).intValue())
                .isEqualTo(INIT_SCORE);

    }
}

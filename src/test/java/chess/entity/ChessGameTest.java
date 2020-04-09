package chess.entity;

import chess.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @DisplayName("이미 종료된 게임을 끝내려 할 경우 Exception 발생")
    @Test
    void endGame() {
        //given
        ChessGame chessGame = new ChessGame(false);

        //then
        assertThatThrownBy(() -> chessGame.endGame(Team.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 종료된 게임입니다.");
    }

    @DisplayName("BLAKC, WHITE가 아닌 팀으로 게임을 끝내려 할 경우 Exception 발생")
    @Test
    void endGame1() {
        //given
        ChessGame chessGame = new ChessGame(true);

        //then
        assertThatThrownBy(() -> chessGame.endGame(Team.NOTHING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("무승부 상태로 게임을 끝낼 수 없습니다.");
    }

    @DisplayName("활성화 상태의 게임을 끝내기")
    @Test
    void name() {
        //given
        ChessGame chessGame = new ChessGame(true);

        //when
        chessGame.endGame(Team.BLACK);

        //then
        assertThat(chessGame.getWinner()).isEqualTo(Team.BLACK);
    }
}
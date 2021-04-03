package chess.domain.manager;

import chess.service.ChessGame;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void initGame(){
        chessGame = new ChessGame();
    }

    @DisplayName("첫 시작으로 흑팀이 움직이면 예외를 발생한다.")
    @Test
    void validateTurn() {
        assertThrows(IllegalArgumentException.class,
                () -> chessGame.validateTurn(new Position("a7")));
    }

    @DisplayName("반대편으로 턴이 넘어간다.")
    @Test
    void validateTurnChange() {
        chessGame.validateTurn(new Position("a2"));
        chessGame.changeTurn();
        chessGame.validateTurn(new Position("a7"));
    }

    @DisplayName("왕이 죽지 않은 상태에서 게임이 종료되었는지 확인한다.")
    @Test
    void isGameEnd() {
        assertThat(chessGame.isGameEnd()).isFalse();
    }

    @DisplayName("왕이 죽은 상태에서 게임이 종료되었는지 확인한다.")
    @Test
    void isGameEndWhenKingDead(){
        chessGame.move(new Position("c7"), new Position("c6"));
        chessGame.move(new Position("d8"), new Position("a5"));
        chessGame.move(new Position("d2"), new Position("d3"));
        chessGame.move(new Position("a5"), new Position("e1"));

        assertThat(chessGame.isGameEnd()).isTrue();
    }

    @DisplayName("외부에서 게임을 종료시킬 수 있다.")
    @Test
    void setGameEnd(){
        assertThat(chessGame.isGameEnd()).isFalse();
        chessGame.setGameEnd();
        assertThat(chessGame.isGameEnd()).isTrue();
    }
}
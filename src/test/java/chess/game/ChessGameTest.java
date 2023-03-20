package chess.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("게임이 대기 상태일때 체스말을 움직이면 예외가 발생한다.")
    void ChessGame_MoveWhenWaitingState() {
        // given
        Position target = Position.of(1, 1);
        Position source = Position.of(2, 2);

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(target, source))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 대기중)");
    }

    @Test
    @DisplayName("게임이 종료 상태일때 체스말을 움직이면 예외가 발생한다.")
    void ChessGame_MoveWhenEndState() {
        // given
        Position target = Position.of(1, 1);
        Position source = Position.of(2, 2);

        // when
        chessGame.end();

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(target, source))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }

    @Test
    @DisplayName("게임이 진행 상태일때 게임을 시작하면 예외가 발생한다.")
    void ChessGame_StartWhenRunningState() {
        // when
        chessGame.start();

        // then
        assertThatThrownBy(() -> chessGame.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)");
    }

    @Test
    @DisplayName("게임이 종료 상태일때 게임을 시작하면 예외가 발생한다.")
    void ChessGame_StartWhenEndState() {
        // when
        chessGame.end();

        // then
        assertThatThrownBy(() -> chessGame.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }

    @Test
    @DisplayName("게임이 대기 상태일때 체스판을 가져오면 예외가 발생한다.")
    void ChessGame_GetBoardWhenWaitingState() {
        // expect
        assertThatThrownBy(() -> chessGame.getBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 대기중)");
    }


    @Test
    @DisplayName("게임이 종료 상태일때 체스판을 가져오면 예외가 발생한다.")
    void ChessGame_GetBoardWhenEndState() {
        // when
        chessGame.end();

        // expect
        assertThatThrownBy(() -> chessGame.getBoard())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 종료됨)");
    }
}

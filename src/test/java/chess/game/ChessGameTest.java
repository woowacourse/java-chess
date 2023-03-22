package chess.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Position;
import chess.game.state.running.BlackTurnState;
import chess.game.state.running.WhiteTurnState;
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
        // given
        TurnStrategy turnStrategy = new RandomTurnStrategy();

        // when
        chessGame.start(turnStrategy);

        // then
        assertThatThrownBy(() -> chessGame.start(turnStrategy))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 잘못된 게임의 상태 입니다.(상태: 진행중)");
    }

    @Test
    @DisplayName("게임이 종료 상태일때 게임을 시작하면 예외가 발생한다.")
    void ChessGame_StartWhenEndState() {
        // given
        TurnStrategy turnStrategy = new RandomTurnStrategy();

        // when
        chessGame.end();

        // then
        assertThatThrownBy(() -> chessGame.start(turnStrategy))
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

    @Test
    @DisplayName("나의 턴이 아닌데 말을 움직이면 예외가 발생한다.")
    void move_Invalid_Turn() {
        // given
        chessGame.start(new MockTurnStrategy(BlackTurnState.STATE));

        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);

        // expect
        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("턴을 바꾸면 상대방의 턴으로 넘어가야 한다.")
    void move_Next_Turn() {
        // given
        chessGame.start(new MockTurnStrategy(WhiteTurnState.STATE));

        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);

        // when
        chessGame.changeTurn();

        // then
        assertThatThrownBy(() -> chessGame.movePiece(source, target))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }
}

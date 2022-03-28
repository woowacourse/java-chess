package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Abscissa;
import chess.domain.Ordinate;
import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Running;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {
    private GameState state;

    @BeforeEach
    void setUp() {
        state = new Ready();
    }

    @Test
    @DisplayName("Ready 상태에서 보드를 초기화하면 Running 상태가 된다")
    void initBoard() {
        GameState newState = state.initBoard();
        assertThat(newState).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("Ready 상태에서 말을 움직이려고 하면 예외가 발생한다")
    void movePieceWhenReadyState() {
        assertThatThrownBy(() -> state.movePiece(Position.valueOf(Abscissa.a, Ordinate.ONE),
            Position.valueOf(Abscissa.b, Ordinate.ONE)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("Ready 상태에서 게임을 종료하려고 하면 예외가 발생한다")
    void endWhenReadyState() {
        assertThatThrownBy(() -> state.end())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("Ready 보드의 상태를 꺼내려고 하면 예외가 발생한다")
    void getBoardWhenReadyState() {
        assertThatThrownBy(() -> state.getBoard())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("보드가 초기화되지 않은 상태에서 점수 계산을 하려고 하면 에외가 발생한다")
    void calculateScoreWhenReadyState() {
        assertThatThrownBy(() -> state.calculateScore(Color.BLACK))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("보드가 초기화되지 않은 상태에서 승자를 결정하려고 하면 에외가 발생한다")
    void judgeWinnerWhenReadyState() {
        assertThatThrownBy(() -> state.judgeWinner())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 시작되지 않았습니다.");
    }
}

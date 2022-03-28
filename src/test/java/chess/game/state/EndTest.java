package chess.game.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Abscissa;
import chess.domain.Ordinate;
import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EndTest {

    private GameState state;

    @BeforeEach
    void setUp() {
        state = new Ready().initBoard().end();
    }

    @Test
    @DisplayName("게임이 종료되어 보드를 초기화하려고 하면 예외가 발생한다")
    void initBoardException() {
        assertThatThrownBy(() -> state.initBoard())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 종료되어 지원하지 않는 기능입니다");
    }

    @Test
    @DisplayName("게임이 종료되고 말을 움직이려고 하면 예외가 발생한다")
    void movePieceException() {
        assertThatThrownBy(() -> state.movePiece(Position.valueOf(Abscissa.a, Ordinate.ONE),
            Position.valueOf(Abscissa.a, Ordinate.TWO)))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 종료되어 지원하지 않는 기능입니다");
    }

    @Test
    @DisplayName("게임이 종료되고 종료 버튼을 누르면 예외가 발생한다")
    void endException() {
        assertThatThrownBy(() -> state.end())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 종료되어 지원하지 않는 기능입니다");
    }

    @Test
    @DisplayName("게임이 종료되고 보드 상황을 확인하려고 하면 예외가 발생한다")
    void getBoardException() {
        assertThatThrownBy(() -> state.getBoard())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 종료되어 지원하지 않는 기능입니다");
    }
}

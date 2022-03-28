package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Abscissa;
import chess.domain.Ordinate;
import chess.domain.Position;
import chess.domain.game.state.End;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Running;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    private GameState state;

    @BeforeEach
    void setUp() {
        state = new Ready().initBoard();
    }

    @Test
    @DisplayName("게임이 시작되고 체스보드를 다시 초기화하려고 하면, 예외가 발생한다")
    void initBoardException() {
        assertThatThrownBy(() -> state.initBoard())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임 도중에 보드를 초기화할 수 없습니다");
    }

    @Test
    @DisplayName("말을 움직였을 때, King이 잡히지 않았다면 Running 상태에 머무른다")
    void movePiece() {
        state.movePiece(Position.valueOf(Abscissa.a, Ordinate.TWO),
            Position.valueOf(Abscissa.a, Ordinate.THREE));
        assertThat(state).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("말을 움직였을 때, King이 잡히면 End 상태로 변환된다")
    void movePieceAndKingKill() {
        GameState newState = state.movePiece(Position.valueOf(Abscissa.e, Ordinate.SEVEN),
                Position.valueOf(Abscissa.e, Ordinate.FIVE))
            .movePiece(Position.valueOf(Abscissa.d, Ordinate.TWO),
                Position.valueOf(Abscissa.d, Ordinate.FOUR))
            .movePiece(Position.valueOf(Abscissa.e, Ordinate.FIVE),
                Position.valueOf(Abscissa.d, Ordinate.FOUR))
            .movePiece(Position.valueOf(Abscissa.d, Ordinate.ONE),
                Position.valueOf(Abscissa.d, Ordinate.THREE))
            .movePiece(Position.valueOf(Abscissa.a, Ordinate.SEVEN),
                Position.valueOf(Abscissa.a, Ordinate.SIX))
            .movePiece(Position.valueOf(Abscissa.d, Ordinate.THREE),
                Position.valueOf(Abscissa.e, Ordinate.THREE))
            .movePiece(Position.valueOf(Abscissa.a, Ordinate.SIX),
                Position.valueOf(Abscissa.a, Ordinate.FIVE))
            .movePiece(Position.valueOf(Abscissa.e, Ordinate.THREE),
                Position.valueOf(Abscissa.e, Ordinate.EIGHT));
        assertThat(newState).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임을 종료하면 상태가 End로 변환된다")
    void end() {
        GameState newState = state.end();
        assertThat(newState).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("Running 상태에서 isFinish()는 False를 반환한다")
    void isFinish() {
        assertThat(state.isFinish()).isFalse();
    }

    @Test
    @DisplayName("보드가 초기화되었을 때, Black과 white 두팀은 38점이다")
    void calculateScore() {
        assertThat(state.calculateScore(Color.WHITE)).isEqualTo(38.0);
        assertThat(state.calculateScore(Color.BLACK)).isEqualTo(38.0);
    }

    @Test
    @DisplayName("King이 잡히지 않은 상황에서, 승자를 판정하려고 하면 예외가 발생한다")
    void judgeWinner() {
        assertThatThrownBy(() -> state.judgeWinner())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 킹이 잡혀야 게임의 승패가 결정됩니다.");
    }
}

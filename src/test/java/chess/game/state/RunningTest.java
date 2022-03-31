package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import chess.domain.game.state.End;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Running;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    private ChessGame state;

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
        state.movePiece(Position.valueOf(File.A, Rank.TWO),
                Position.valueOf(File.A, Rank.THREE));
        assertThat(state).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("말을 움직였을 때, King이 잡히면 End 상태로 변환된다")
    void movePieceAndKingKill() {
        ChessGame newState = state.movePiece(Position.valueOf(File.D, Rank.TWO),
                        Position.valueOf(File.D, Rank.FOUR))
                .movePiece(Position.valueOf(File.E, Rank.SEVEN),
                        Position.valueOf(File.E, Rank.FIVE))
                .movePiece(Position.valueOf(File.D, Rank.ONE),
                        Position.valueOf(File.D, Rank.THREE))
                .movePiece(Position.valueOf(File.E, Rank.FIVE),
                        Position.valueOf(File.D, Rank.FOUR))
                .movePiece(Position.valueOf(File.D, Rank.THREE),
                        Position.valueOf(File.E, Rank.THREE))
                .movePiece(Position.valueOf(File.A, Rank.SEVEN),
                        Position.valueOf(File.A, Rank.SIX))
                .movePiece(Position.valueOf(File.E, Rank.THREE),
                        Position.valueOf(File.E, Rank.EIGHT));
        assertThat(newState).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임을 종료하면 상태가 End로 변환된다")
    void end() {
        ChessGame newState = state.end();
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

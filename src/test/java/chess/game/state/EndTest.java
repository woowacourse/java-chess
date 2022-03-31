package chess.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import chess.domain.piece.Color;
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
        assertThatThrownBy(() -> state.movePiece(Position.valueOf(File.a, Rank.ONE),
            Position.valueOf(File.a, Rank.TWO)))
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
    void calculateScoreException() {
        assertThatThrownBy(() -> state.calculateScore(Color.WHITE))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 게임이 종료되어 지원하지 않는 기능입니다");
    }

    @Test
    @DisplayName("게임을 강제 종료했을 때 승패 판정을 하려고 하면 예외가 발생한다 ")
    void judgeWinnerException() {
        assertThatThrownBy(() -> state.judgeWinner())
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("게임이 종료되어 승자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("게임이 King이 잡혀 종료되었을 때, 승패 판정을 한다. White가 이긴 경우")
    void judgeWinnerWhite() {
        GameState state = new Ready().initBoard()
            .movePiece(Position.valueOf(File.e, Rank.SEVEN),
                Position.valueOf(File.e, Rank.FIVE))
            .movePiece(Position.valueOf(File.d, Rank.TWO),
                Position.valueOf(File.d, Rank.FOUR))
            .movePiece(Position.valueOf(File.e, Rank.FIVE),
                Position.valueOf(File.d, Rank.FOUR))
            .movePiece(Position.valueOf(File.d, Rank.ONE),
                Position.valueOf(File.d, Rank.THREE))
            .movePiece(Position.valueOf(File.a, Rank.SEVEN),
                Position.valueOf(File.a, Rank.SIX))
            .movePiece(Position.valueOf(File.d, Rank.THREE),
                Position.valueOf(File.e, Rank.THREE))
            .movePiece(Position.valueOf(File.a, Rank.SIX),
                Position.valueOf(File.a, Rank.FIVE))
            .movePiece(Position.valueOf(File.e, Rank.THREE),
                Position.valueOf(File.e, Rank.EIGHT));

        assertThat(state.judgeWinner()).isEqualTo(Color.WHITE);
    }
}

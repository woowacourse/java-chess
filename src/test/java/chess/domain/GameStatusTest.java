package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStatusTest {

    @Test
    @DisplayName("이미 게임이 시작한 후 라면 예외가 터진다.")
    void start() {
        // given
        final GameStatus gameStatus = new GameStatus();
        gameStatus.start();

        // then
        assertThatThrownBy(() -> gameStatus.start())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 시작되었습니다.");
    }

    @Test
    @DisplayName("게임을 시작하고 차례에 맞는 색의 기물을 이동시킬 수 있는지 확인한다.")
    void checkCanMove() {
        // given
        final GameStatus gameStatus = new GameStatus();
        gameStatus.start();
        final ChessPiece chessPiece = Pawn.from(Color.WHITE);

        // then
        assertThatCode(() -> gameStatus.checkCanMove(chessPiece))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("게임 시작 전에 이동을 하려고 하면 예외가 터진다.")
    void checkCanMove_before_start() {
        // given
        final GameStatus gameStatus = new GameStatus();

        // then
        assertThatThrownBy(() -> gameStatus.checkCanMove(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("게임이 이미 종료된 후에 이동하려고 하면 예외가 터진다.")
    void checkCanMove_after_end() {
        // given
        final GameStatus gameStatus = new GameStatus();
        gameStatus.end();

        // then
        assertThatThrownBy(() -> gameStatus.checkCanMove(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }

    @Test
    @DisplayName("킹이 잡힌 후에 이동하려고 하면 예외가 터진다.")
    void checkCanMove_after_king_die() {
        // given
        final GameStatus gameStatus = new GameStatus();
        gameStatus.kingDie();

        // then
        assertThatThrownBy(() -> gameStatus.checkCanMove(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }

    @Test
    @DisplayName("차례가 아닌 기물을 이동하려고 하면 예외가 터진다.")
    void checkCanMove_enemy_piece() {
        // given
        final GameStatus gameStatus = new GameStatus();
        gameStatus.start();

        // then
        assertThatThrownBy(() -> gameStatus.checkCanMove(Pawn.from(Color.BLACK)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Color.WHITE.name() + "의 차례입니다.");
    }

    @Test
    @DisplayName("게임을 시작하지 않고 점수를 계산하려고 하면 예외가 터진다.")
    void checkCanCalculateScore() {
        // given
        final GameStatus gameStatus = new GameStatus();

        // then
        assertThatThrownBy(() -> gameStatus.checkCanCalculateScore())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }
}

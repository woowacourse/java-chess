package chess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    @DisplayName("초기 상태의 게임을 시작한다.")
    void createInitialGame() {
        Game game = new Game();

        assertThat(game).isNotNull();
    }

    @Test
    @DisplayName("게임이 진행 가능한지 확인한다.")
    void testIsRunnable() {
        Game game = new Game();
        assertThat(game.isRunnable()).isTrue();
    }

    @Test
    @DisplayName("게임 시작 명령 후에 게임은 진행 가능하다.")
    void testStartCommand() {
        Game game = new Game();

        game.execute(Command.START);

        assertThat(game.isRunnable()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 명령 후에 게임은 진행은 불가능하다.")
    void testFinishCommand() {
        Game game = new Game();
        game.execute(Command.FINISH);
        assertThat(game.isRunnable()).isFalse();
    }
}

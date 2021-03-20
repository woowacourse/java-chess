package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameOverTest {

    @Test
    @DisplayName("게임 오버 여부가 바뀌는 기능")
    void changeGameOver() {
        final GameOver gameOver = new GameOver();
        gameOver.changeGameOver();
        assertThat(gameOver.isGameOver()).isTrue();
    }

}
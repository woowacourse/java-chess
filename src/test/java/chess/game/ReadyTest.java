package chess.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("준비 상태를 생성한다.")
    void createStateTest() {
        GameState gameState = new Ready();

        assertThat(gameState).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 시작 상태로 변한다.")
    void startTest() {
        GameState gameState = new Ready();

        assertThat(gameState.start()).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("게임 종료시 종료 상태로 변한다.")
    void finishTest() {
        GameState gameState = new Ready();

        assertThat(gameState.finish()).isInstanceOf(Finished.class);
    }
}

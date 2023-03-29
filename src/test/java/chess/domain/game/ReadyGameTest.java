package chess.domain.game;

import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyGameTest {
    
    @Test
    @DisplayName("준비 상태 게임 시작 테스트")
    void start_game_test() {
        ReadyGame readyGame = new ReadyGame();
        Game2 game = readyGame.start();
        Assertions.assertThat(game).isInstanceOf(RunGame.class);
    }
    
    @Test
    @DisplayName("준비상태 게임 메서드 호출 예외 테스트 - move")
    void ready_game_exception_test() {
        ReadyGame readyGame = new ReadyGame();
        Assertions.assertThatThrownBy(() -> readyGame.move(Position.from("a2"), Position.from("a3")))
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("준비상태 게임 메서드 호출 예외 테스트 - end")
    void ready_game_exception_test2() {
        ReadyGame readyGame = new ReadyGame();
        Assertions.assertThatThrownBy(readyGame::end)
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("준비상태 게임 메서드 호출 예외 테스트 - status")
    void ready_game_exception_test3() {
        ReadyGame readyGame = new ReadyGame();
        Assertions.assertThatThrownBy(readyGame::status)
                .isInstanceOf(IllegalStateException.class);
    }
    
}
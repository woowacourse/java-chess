package chess.domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndGameTest {
    
    @Test
    @DisplayName("종료 상태 게임 메서드 호출 예외 테스트 - move")
    void end_game_exception_test() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(() -> endGame.move(null, null))
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("종료 상태 게임 메서드 호출 예외 테스트 - start")
    void end_game_exception_test2() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::start)
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("종료 상태 게임 메서드 호출 예외 테스트 - end")
    void end_game_exception_test3() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::end)
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("종료 상태 게임 메서드 호출 예외 테스트 - status")
    void end_game_exception_test4() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::status)
                .isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    @DisplayName("종료 상태 게임 메서드 호출 예외 테스트 - getBoard")
    void end_game_exception_test5() {
        EndGame endGame = new EndGame();
        Assertions.assertThatThrownBy(endGame::getBoard)
                .isInstanceOf(IllegalStateException.class);
    }
    
}
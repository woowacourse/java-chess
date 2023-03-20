package chessgame;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import chessgame.domain.Command;
import chessgame.domain.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    Game game;

    @BeforeEach
    void before() {
        game = new Game();
    }

    @Test
    @DisplayName("상태를 변경할 수 있다.")
    void Should_ChangeStatus_When_SetState(){
        Assertions.assertThatNoException().isThrownBy(()->game.setState(Command.of("start")));
    }

    @Test
    @DisplayName("상태가 end인지 확인할 수 있다.")
    void Should_Check_When_IsEnd(){
        game.setState(Command.of("end"));
        Assertions.assertThat(game.isEnd()).isTrue();
    }
}

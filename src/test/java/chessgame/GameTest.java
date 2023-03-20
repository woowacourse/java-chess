package chessgame;

import org.junit.jupiter.api.BeforeEach;

import chessgame.domain.Command;
import chessgame.domain.Game;

class GameTest {

    Game game;

    @BeforeEach
    void before() {
        game = new Game();
        game.setState(Command.of("start"));
    }

}

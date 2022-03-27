package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.status.Ready;
import chess.view.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("킹이 죽으면 게임이 종료된다.")
    void gameEnd() {
        Game game = new Game(Ready.run(Command.START));
        game.run("move b1 c3");
        game.run("move h7 h6");
        game.run("move c3 d5");
        game.run("move h6 h5");
        game.run("move d5 c7");
        game.run("move h5 h4");
        game.run("move c7 e8");

        assertThatThrownBy(() -> game.run("move h4 h3"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }
}

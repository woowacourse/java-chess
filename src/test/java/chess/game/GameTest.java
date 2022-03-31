package chess.game;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.status.Ready;
import chess.view.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class GameTest {

    @Test
    @DisplayName("킹이 죽으면 게임이 종료된다.")
    void gameEnd() {
        Game game = new Game(Ready.start(Command.START));
        game.run("move", List.of("move", "b1", "c3"));
        game.run("move", List.of("move", "h7", "h6"));
        game.run("move", List.of("move", "c3", "d5"));
        game.run("move", List.of("move", "h6", "h5"));
        game.run("move", List.of("move", "d5", "c7"));
        game.run("move", List.of("move", "h5", "h4"));
        game.run("move", List.of("move", "c7", "e8"));

        assertThatThrownBy(() -> game.run("move", List.of("move", "h4", "h3")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임이 이미 종료되었습니다.");
    }
}

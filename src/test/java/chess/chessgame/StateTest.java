package chess.chessgame;

import chess.state.Finish;
import chess.state.Play;
import chess.state.Ready;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StateTest {

    @Test
    @DisplayName("ready -> play")
    void check() {
        Ready ready = new Ready();

        assertThat(ready.start()).isInstanceOf(Play.class);
    }

    @Test
    @DisplayName("play -> finish")
    void playToFinish() {
        Play play = new Play();

        assertThat(play.end()).isInstanceOf(Finish.class);
    }

}

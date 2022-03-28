package chess;

import chess.domain.Turn;
import chess.domain.state.Finish;
import chess.domain.state.Play;
import chess.domain.state.Ready;

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
        Play play = new Play(new Turn());

        assertThat(play.end()).isInstanceOf(Finish.class);
    }
}

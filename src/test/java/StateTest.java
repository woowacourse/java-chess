import chess.Finish;
import chess.Play;
import chess.Ready;
import chess.State;
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

        assertThat(play.move()).isInstanceOf(Finish.class);
    }
}

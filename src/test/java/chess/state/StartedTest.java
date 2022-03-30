package chess.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.piece.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    private State started;

    @BeforeEach
    void setUp() {
        started = new Started(Color.WHITE, Board.create());
    }

    @Test
    @DisplayName("started state에서 start를 한 경우 예외가 발생하는지 확인한다.")
    void start() {
        Assertions.assertThatThrownBy(() -> started.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이미 start를 하여 다시 start를 할 수 없습니다.");
    }

    @Test
    @DisplayName("started state에서 end를 한 경우 ended가 반환되는지 확인한다.")
    void end() {
        assertThat(started.end()).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("started state에서 move를 한 경우 started 하는지 확인한다.")
    void move() {
        String[] commands = {"move", "b2", "b3"};
        assertThat(started.move(commands)).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("started state에서 move를 한 경우, command가 format과 맞지 않을 때 예외가 발생하는지 확인한다.")
    void exceptionMove() {
        String[] commands = {"move", "b2"};
        Assertions.assertThatThrownBy(() -> started.move(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] move command는 source와 target이 필요합니다.");
    }

    @Test
    @DisplayName("Started state에서 체크메이트를 한 경우 Ended 상태가 된다.")
    void moveCheckmate() {
        started = started.move(new String[]{"move", "a2", "a4"});
        started = started.move(new String[]{"move", "e7", "e5"});
        started = started.move(new String[]{"move", "a4", "a5"});
        started = started.move(new String[]{"move", "e5", "e4"});
        started = started.move(new String[]{"move", "a1", "a4"});
        started = started.move(new String[]{"move", "e4", "e3"});
        started = started.move(new String[]{"move", "a4", "e4"});
        started = started.move(new String[]{"move", "h7", "h5"});

        assertThat(started.move(new String[]{"move", "e4", "e8"})).isInstanceOf(Ended.class);
    }

    @Test
    @DisplayName("Started state에서 status를 한 경우 started 상태가 된다.")
    void status() {
        assertThat(started.status()).isInstanceOf(Started.class);
    }

}

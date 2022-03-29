package chess.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Board;
import chess.domain.BoardInitializer;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    private State started;

    @BeforeEach
    void setUp() {
        Board board = new Board(BoardInitializer.create());
        started = new Started(Color.WHITE, board);
    }

    @Test
    void start() {
        assertThat(started.start()).isInstanceOf(Started.class);
    }

    @Test
    void end() {
        assertThat(started.end()).isInstanceOf(Ended.class);
    }

    @Test
    void move() {
        String[] commands = {"move", "b2", "b3"};
        assertThat(started.move(commands)).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("Started 상태에서 체크메이트를 한 경우 Ended 상태가 된다.")
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
    void status() {
        assertThat(started.status()).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("move의 source 위치와 target 위치가 부적절한 경우 예외를 발생시킨다.")
    void exceptionWhenIllegalPosition() {
        String[] commands = {"move", "b3"};
        assertThatThrownBy(() -> started.move(commands)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] source 위치와 target 위치를 입력해주세요.");
    }
}

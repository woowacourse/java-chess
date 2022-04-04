package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartedTest {

    private State started;

    @BeforeEach
    void setUp() {
        Board board = new Board(new BasicBoardFactory());
        started = new Started(Color.WHITE, board);
    }

    @Test
    void start() {
        assertThatThrownBy(() -> started.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 이미 start를 하여 다시 start를 할 수 없습니다.");
    }

    @Test
    void end() {
        assertThat(started.end()).isInstanceOf(Ended.class);
    }

    @Test
    void move() {
        Position from = Position.create("b2");
        Position to = Position.create("b3");

        assertThat(started.move(from, to)).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("Started 상태에서 체크메이트를 한 경우 Ended 상태가 된다.")
    void moveCheckmate() {
        started = started.move(Position.create("a2"), Position.create("a4"));
        started = started.move(Position.create("e7"), Position.create("e5"));
        started = started.move(Position.create("a4"), Position.create("a5"));
        started = started.move(Position.create("e5"), Position.create("e4"));
        started = started.move(Position.create("a1"), Position.create("a4"));
        started = started.move(Position.create("e4"), Position.create("e3"));
        started = started.move(Position.create("a4"), Position.create("e4"));
        started = started.move(Position.create("h7"), Position.create("h5"));

        assertThat(started.move(Position.create("e4"), Position.create("e8")))
                .isInstanceOf(Ended.class);
    }

    @Test
    void status() {
        assertThat(started.status()).isInstanceOf(Started.class);
    }
}

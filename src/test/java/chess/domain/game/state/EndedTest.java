package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BasicBoardFactory;
import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EndedTest {

    private State ended;

    @BeforeEach
    void setUp() {
        ended = new Ended(new Board(new BasicBoardFactory()));
    }

    @Test
    void start() {
        assertThat(ended.start()).isInstanceOf(Started.class);
    }

    @Test
    void end() {
        assertThatThrownBy(() -> ended.end())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 end 할 수 없습니다.");
    }

    @Test
    void move() {
        Position from = Position.from("b2");
        Position to = Position.from("b3");
        assertThatThrownBy(() -> ended.move(from, to))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 move 할 수 없습니다.");
    }

    @Test
    void status() {
        assertThatThrownBy(() -> ended.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 게임이 끝나 status 할 수 없습니다.");
    }

}

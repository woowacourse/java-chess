package chess.domain.gamestate;

import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.board.BoardTestInitializer;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RunningTest {
    private State running;

    @BeforeEach
    void setUp() {
        running = new Running(new Board(BoardTestInitializer.init()), Side.WHITE);
    }

    @Test
    @DisplayName("finished 메서드 테스트")
    void finished() {
        assertThat(running.finished()).isInstanceOf(Finished.class);
    }

    @Test
    @DisplayName("기물을 움직여 왕을 잡을 경우 GameSet 상태로 변화")
    void moveGameSet() {
        assertThat(running.move(Position.from("d5"), Position.from("f6"))).isInstanceOf(GameSet.class);
    }

    @Test
    @DisplayName("기물을 움직여 Running 상태로 변화")
    void moveRunning() {
        assertThat(running.move(Position.from("h2"), Position.from("h3"))).isInstanceOf(Running.class);
    }
}
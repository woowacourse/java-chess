package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinishedTest {
    @Test
    @DisplayName("start를 실행했을 때 예외 발생")
    public void start() {
        assertThatThrownBy(() -> {
            new Finished().start();
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이미 게임이 끝났습니다.");
    }

    @Test
    @DisplayName("move를 실행했을 때 예외 발생")
    public void move() {
        assertThatThrownBy(() -> {
            new Finished().move(
                    new Grid(new NormalGridStrategy()),
                    new Position('a', '2'),
                    new Position('a', '3'));
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이미 게임이 끝났습니다.");
    }

    @Test
    @DisplayName("end를 실행했을 때 예외 발생")
    public void end() {
        assertThatThrownBy(() -> {
            new Finished().end();
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이미 게임이 끝났습니다.");
    }

    @Test
    @DisplayName("status를 실행했을 때 예외 발생")
    public void status() {
        assertThatThrownBy(() -> {
            new Finished().status();
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("이미 게임이 끝났습니다.");
    }
}

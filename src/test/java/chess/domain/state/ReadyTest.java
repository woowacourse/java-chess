package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.position.Position;
import chess.exception.ChessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ReadyTest {
    @Test
    @DisplayName("start를 실행했을 때 Playing 상태를 반환")
    public void start() {
        assertThat(new Ready().start().getClass()).isEqualTo(Playing.class);
    }

    @Test
    @DisplayName("end를 실행했을 때 Finished 상태를 반환")
    public void end() {
        assertThat(new Ready().end().getClass()).isEqualTo(Finished.class);
    }

    @Test
    @DisplayName("move를 실행했을 때 예외 발생")
    public void move() {
        assertThatThrownBy(() -> {
            new Ready().move(
                    new Grid(new NormalGridStrategy()),
                    new Position('a', '2'),
                    new Position('a', '3'));
        }).isInstanceOf(ChessException.class).hasMessage("아직 게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName("status를 실행했을 때 예외 발생")
    public void status() {
        assertThatThrownBy(() -> {
            new Ready().status();
        }).isInstanceOf(ChessException.class).hasMessage("아직 게임이 시작되지 않았습니다.");
    }
}

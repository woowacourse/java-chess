package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.player.Player;
import chess.domain.result.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndStateTest {

    EndState endState;


    @BeforeEach
    void setUp() {
        Map<Player, Double> status = new HashMap<>();
        status.put(Player.WHITE, 0d);
        status.put(Player.BLACK, 0d);
        endState = new EndState(new Status(status));
    }

    @Test
    @DisplayName("EndState는 Start 불가능")
    void start() {
        assertThatThrownBy(() -> endState.start())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 move 메서드를 지원하지 않음")
    void move() {
        assertThatThrownBy(() -> endState.move(MoveParameter.of(Arrays.asList("B1", "A3")), Turn.from(Player.WHITE)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 end 메서드를 지원하지 않음")
    void end() {
        assertThatThrownBy(() -> endState.end())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 getBoard 메서드를 지원하지 않음")
    void getBoard() {
        assertThatThrownBy(() -> endState.getBoard())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("EndState는 getStatus 시 결과를 반환함")
    void getStatus() {
        assertThat(endState.getStatus())
                .isInstanceOf(Status.class);
    }
}
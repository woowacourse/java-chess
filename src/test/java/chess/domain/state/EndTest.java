package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Command;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    @DisplayName("End 상태인 경우 명령을 실행할 수 없다.")
    void execute() {
        GameState gameState = new End();
        Command command = Command.END;
        List<String> input = List.of("end");

        assertThatThrownBy(() -> gameState.execute(command, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

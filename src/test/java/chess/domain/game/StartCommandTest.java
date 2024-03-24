package chess.domain.game;

import static chess.domain.game.StartCommand.START_COMMAND;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartCommandTest {
    @Test
    @DisplayName("지원하지 않는 메서드인지 검증")
    void getOptions() {
        Assertions.assertThatThrownBy(START_COMMAND::getOptions)
                .isInstanceOf(UnsupportedOperationException.class);
    }

}

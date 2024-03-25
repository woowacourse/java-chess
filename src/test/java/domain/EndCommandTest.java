package domain;

import static domain.command.EndCommand.END_COMMAND;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndCommandTest {

    @Test
    @DisplayName("지원하지 않는 메서드인지 검증")
    void getOptions() {
        Assertions.assertThatThrownBy(END_COMMAND::getOptions)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}

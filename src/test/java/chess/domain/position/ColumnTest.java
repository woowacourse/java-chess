package chess.domain.position;

import chess.config.BoardConfig;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColumnTest {
    @Test
    void constructSucceed() {
        int validValue = BoardConfig.LINE_START;
        Column column = new Column(validValue);
        assertThat(column).isNotNull();

        validValue = BoardConfig.LINE_END;
        column = new Column(validValue);
        assertThat(column).isNotNull();
    }

    @Test
    void constructFail() {
        int insufficientValue = BoardConfig.LINE_START - 1;
        assertThatThrownBy(() -> new Column(insufficientValue))
                .isInstanceOf(IllegalArgumentException.class);

        int exceedValue = BoardConfig.LINE_END + 1;
        assertThatThrownBy(() -> new Column(exceedValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
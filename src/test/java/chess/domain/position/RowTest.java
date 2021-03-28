package chess.domain.position;

import chess.domain.position.exception.InvalidRowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RowTest {
    @Test
    void fromSuccess() {
        Row from = Row.from(1);
        assertThat(from).isEqualTo(Row.FIRST);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9, 123})
    void fromFail(int input) {
        assertThatThrownBy(() -> Row.from(input))
                .isInstanceOf(InvalidRowException.class);
    }

    @Test
    void nextRowSuccess() {
        assertThat(Row.FIRST.nextRow(1)).isEqualTo(Row.SECOND);
        assertThat(Row.FIRST.nextRow(7)).isEqualTo(Row.EIGHTH);
    }

    @Test
    void nextRowFail() {
        assertThatThrownBy(() -> Row.FIRST.nextRow(8))
                .isInstanceOf(InvalidRowException.class);
    }
}
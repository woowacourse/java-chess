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
        Row from = Row.from('1');
        assertThat(from).isEqualTo(Row.FIRST);
    }

    @ParameterizedTest
    @ValueSource(chars = {'ㄱ', 'z', 'i', '0', '9'})
    void fromFail(Character input) {
        assertThatThrownBy(() -> Row.from(input))
                .isInstanceOf(InvalidRowException.class);
    }
}
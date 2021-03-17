package chess.domain.position;

import chess.domain.position.exception.InvalidColumnException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColumnTest {
    @Test
    void fromSuccess() {
        Column from = Column.from('a');
        assertThat(from).isEqualTo(Column.A);
    }

    @ParameterizedTest
    @ValueSource(chars = {'ㄱ', 'z', 'i', '1'})
    void fromFail(Character input) {
        assertThatThrownBy(() -> Column.from(input))
                .isInstanceOf(InvalidColumnException.class);
    }
}
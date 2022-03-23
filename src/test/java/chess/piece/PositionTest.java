package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"a,1", "b,5", "h,8"})
    @DisplayName("행과 열이 같으면 동일하다.")
    void cachedPosition(final String column, final int row) {
        assertThat(Position.of(column, row)).isSameAs(Position.of(column, row));
    }
}

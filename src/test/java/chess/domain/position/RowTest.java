package chess.domain.position;

import static chess.domain.position.Row.INVALID_RANGE_OF_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {

    @ParameterizedTest(name = "행은 최소 0, 최대 7을 가질 수 있다. 입력값: {0}")
    @ValueSource(ints = {-1 ,8})
    void 행은_최소_0_최대_7을_가질_수_있다_실패(int row) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Row(row)
        ).withMessage(INVALID_RANGE_OF_VALUE + row);
    }

    @ParameterizedTest(name = "행은 최소 0, 최대 7을 가질 수 있다. 입력값: {0}")
    @ValueSource(ints = {0 ,7})
    void 행은_최소_0_최대_7을_가질_수_있다_성공(int row) {
        assertThatNoException().isThrownBy(
                () -> new Row(row)
        );
    }

    @Test
    @DisplayName("논리적으로 같은 객체이다.")
    void 논리적으로_같은_객체이다() {
        Row row = new Row(1);
        Row otherRow = new Row(1);

        assertThat(row.equals(otherRow)).isTrue();
    }

    @Test
    @DisplayName("값을 받아 더할 수 있다")
    void 값을_받아_더할_수_있다() {
        Row row = new Row(0);
        Row newRow = row.add(1);

        assertThat(newRow.getRow()).isEqualTo(1);
    }
}

package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ColumnPositionTest {
    @DisplayName("실패 : 지정된 범위가 아닌 열 번호가 주어지면 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    void should_ThrowIllegaStateException_When_GiveOutRangedColumnNumber(int outRangedColumnNumber) {
        assertThatThrownBy(() -> new ColumnPosition(outRangedColumnNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("체스판의 열 번호는 " + outRangedColumnNumber + "가 될 수 없습니다.");
    }

    @DisplayName("성공 : 지정된 범위의 행 번호가 주어지면 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void should_MakeColumnObject_When_GiveValidRangeColumnNumber(int validRangedColumnNumber) {
        assertThatCode(() -> new ColumnPosition(validRangedColumnNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("두 열 번호의 차이를 반환할 수 있다")
    @Test
    void should_ReturnDifferenceWithOtherColumnNumber() {
        ColumnPosition testColumnPosition_1 = new ColumnPosition(7);
        ColumnPosition testColumnPosition_2 = new ColumnPosition(0);

        int expectedDifference = 7;

        assertThat(testColumnPosition_1.intervalWith(testColumnPosition_2)).isEqualTo(expectedDifference);
    }
}

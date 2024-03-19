package chess.domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowPositionTest {
    @DisplayName("실패 : 지정된 범위가 아닌 행 번호가 주어지면 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void should_ThrowIllegaStateException_When_GiveOutRangedRowNumber(int outRangedRowNumber) {
        assertThatThrownBy(() -> new RowPosition(outRangedRowNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("체스판의 행 번호는 " + outRangedRowNumber + "가 될 수 없습니다.");
    }

    @DisplayName("성공 : 지정된 범위의 행 번호가 주어지면 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    void should_MakeRowObject_When_GiveValidRangeRowNumber(int validRangedRowNumber) {
        assertThatCode(() -> new RowPosition(validRangedRowNumber))
                .doesNotThrowAnyException();
    }
}

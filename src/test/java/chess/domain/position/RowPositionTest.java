package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RowPositionTest {
    @DisplayName("실패 : 지정된 범위가 아닌 행 번호가 주어지면 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    void should_ThrowIllegaStateException_When_GiveOutRangedRowNumber(int outRangedRowNumber) {
        assertThatThrownBy(() -> new RowPosition(outRangedRowNumber))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("체스판의 행 번호는 " + outRangedRowNumber + "가 될 수 없습니다.");
    }

    @DisplayName("성공 : 지정된 범위의 행 번호가 주어지면 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {0, 7})
    void should_MakeRowObject_When_GiveValidRangeRowNumber(int validRangedRowNumber) {
        assertThatCode(() -> new RowPosition(validRangedRowNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("수직 대칭인 행 번호를 반환할 수 있다")
    @Test
    void should_ReturnVerticalReverseRowNumber() {
        RowPosition testRowPosition = new RowPosition(0);

        assertThat(testRowPosition.reverse()).isEqualTo(new RowPosition(7));
    }

    @DisplayName("두 행 번호의 차이를 반환할 수 있다")
    @Test
    void should_ReturnDifferenceWithOtherRowNumber() {
        RowPosition testRowPosition_1 = new RowPosition(7);
        RowPosition testRowPosition_2 = new RowPosition(0);

        int expectedDifference = 7;

        assertThat(testRowPosition_1.intervalWith(testRowPosition_2)).isEqualTo(expectedDifference);
    }

    @DisplayName("행 번호가 더 높은지 확인할 수 있다")
    @Test
    void should_CheckIsHigherThanOtherRowNumber() {
        RowPosition lowerPosition = new RowPosition(0);
        RowPosition higherPosition = new RowPosition(1);

        assertAll(
                () -> assertThat(higherPosition.isHigherThan(lowerPosition)).isTrue(),
                () -> assertThat(lowerPosition.isHigherThan(higherPosition)).isFalse()
        );
    }

    @DisplayName("행 번호가 더 낮은지 확인할 수 있다")
    @Test
    void should_CheckIsLowerThanOtherRowNumber() {
        RowPosition lowerPosition = new RowPosition(1);
        RowPosition higherPosition = new RowPosition(0);

        assertAll(
                () -> assertThat(higherPosition.isLowerThan(lowerPosition)).isTrue(),
                () -> assertThat(lowerPosition.isLowerThan(higherPosition)).isFalse()
        );
    }
}

package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ColorTest {
    @DisplayName("턴을 변경한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,WHITE", "WHITE,BLACK"})
    void convertTurn(Color firstTurn, Color secondTurn) {
        // when, then
        assertThat(firstTurn.convertTurn()).isEqualTo(secondTurn);
    }

    @DisplayName("현재 턴의 팀 색상이 없으면 턴을 바꿀 수 없다")
    @Test
    void convertInvalidTurn() {
        // given
        Color firstTurn = Color.NONE;

        // when, then
        assertThatThrownBy(firstTurn::convertTurn).isInstanceOf(UnsupportedOperationException.class);
    }
}

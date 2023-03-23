package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CampTest {

    @ParameterizedTest(name = "isSameCamp.BLACK은 {0}이 주어지면 {1}을 반환한다")
    @DisplayName("isSameCamp() 테스트")
    @CsvSource(value = {"BLACK:true", "WHITE:false"}, delimiter = ':')
    void isSameCamp_givenOtherCamp_thenReturnIsSameCamp(final Camp camp, final boolean expected) {
        // when
        final boolean actual = Camp.BLACK.isSameCamp(camp);

        // then
        assertThat(actual).isSameAs(expected);
    }
}

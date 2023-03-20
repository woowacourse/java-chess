package chess.domain.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CampTypeTest {

    @ParameterizedTest(name = "입력받은 위치에 따라 진영을 나눈다.")
    @CsvSource(value = {"a:WHITE", "A:BLACK"}, delimiter = ':')
    void divide(final char columnPosition, final CampType expected) {
        // given, when
        final CampType actual = CampType.divide(columnPosition);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("기존의 진영에서 다른 진영으로 턴을 변경한다.")
    void changeTurn() {
        // given
        CampType white = CampType.WHITE;
        CampType black = CampType.BLACK;

        // when
        CampType whiteActual = white.changeTurn();
        CampType blackActual = black.changeTurn();

        // then
        assertThat(whiteActual)
                .isEqualTo(black);

        assertThat(blackActual)
                .isEqualTo(white);
    }
}

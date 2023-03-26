package chess.domain.piece;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CampTypeTest {

    @ParameterizedTest(name = "입력받은 위치에 따라 진영을 나눈다.")
    @CsvSource(value = {"a:WHITE", "A:BLACK"}, delimiter = ':')
    void divide(final char columnPosition, final TeamColor expected) {
        // given, when
        final TeamColor actual = TeamColor.divide(columnPosition);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

}

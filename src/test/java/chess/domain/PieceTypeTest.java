package chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PieceTypeTest {
    @ParameterizedTest
    @CsvSource(value = {"true:0.5", "false:1.0"}, delimiter = ':')
    void score(boolean mustChange, double expected) {
        assertThat(PieceType.PAWN.score(mustChange)).isEqualTo(expected);
    }
}
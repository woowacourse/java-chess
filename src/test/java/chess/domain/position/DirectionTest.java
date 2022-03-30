package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    @DisplayName("position 으로 방향 구하기")
    @ParameterizedTest
    @CsvSource(value = {"a1,b2,NORTHEAST", "a1,b3,NNE"})
    void getDirection(final Position from, final Position to, final Direction expected) {
        assertThat(Direction.of(from, to)).isEqualTo(expected);
    }
}

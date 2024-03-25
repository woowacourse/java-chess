package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @DisplayName("position 목록에서 해당되는 position을 찾아온다")
    @ParameterizedTest
    @CsvSource(value = {"A,ONE", "C,SIX", "H,EIGHT"})
    void validateCachedPosition_Of(File file, Rank rank) {
        // given, when
        Position position = Position.of(file, rank);

        // then
        assertThat(position).isEqualTo(Position.of(file, rank));
    }
}

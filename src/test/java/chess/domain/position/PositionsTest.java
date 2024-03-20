package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PositionsTest {
    @DisplayName("position목록에서 해당되는 position을 찾아온다")
    @ParameterizedTest
    @CsvSource(value = {"ONE,A", "SIX,C", "EIGHT,H"})
    void validateCachedPosition(Rank rank, File file) {
        // given, when
        Position position = Positions.of(rank, file);

        // then
        assertThat(position).isEqualTo(new Position(rank, file));
    }
}

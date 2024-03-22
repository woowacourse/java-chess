package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @DisplayName("position목록에서 해당되는 position을 찾아온다")
    @ParameterizedTest
    @CsvSource(value = {"A,ONE", "C,SIX", "H,EIGHT"})
    void validateCachedPosition(File file, Rank rank) {
        // given, when
        Position position = Position.of(file, rank);

        // then
        assertThat(position).isEqualTo(Position.of(file, rank));
    }

    @DisplayName("주어진 File, Rank와 일치하는 포지션인지 확인한다.")
    @Test
    void findPosition() {
        // given
        Rank rank = Rank.ONE;
        File file = File.A;
        Position position = Position.of(File.A, Rank.ONE);

        // when
        boolean isRightPosition = position.findPosition(file, rank);

        // then
        assertThat(isRightPosition).isTrue();
    }
}

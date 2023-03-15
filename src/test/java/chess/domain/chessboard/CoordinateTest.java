package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CoordinateTest {

    @Test
    void 좌표는_a부터h_1부터8_이외에는_예외가_발생한다() {
        //given
        final String alphanumeric = "o9";

        //when & then
        assertThatThrownBy(() -> Coordinate.of(alphanumeric)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 좌표는_알파벳숫자를_받아_파싱한다() {
        //given
        final String alphanumeric = "a3";

        //when
        final Coordinate coordinate = Coordinate.of(alphanumeric);

        //then
        assertThat(coordinate)
                .extracting("fileIndex")
                .isEqualTo(0);
        assertThat(coordinate)
                .extracting("rankIndex")
                .isEqualTo(2);
    }
}

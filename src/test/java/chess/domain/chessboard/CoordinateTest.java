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

    @Test
    void 좌표는_입력받은_숫자만큼_상하로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.verticalMove(1)).isEqualTo(Coordinate.of("a2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_좌우로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.horizontalMove(1)).isEqualTo(Coordinate.of("b1"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_양수인_대각선으로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.of("a1");

        //when & then
        assertThat(a1.positiveDiagonalMove(1)).isEqualTo(Coordinate.of("b2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_음수인_대각선으로_움직일_수_있다2() {
        //given
        final Coordinate a1 = Coordinate.of("b1");

        //when & then
        assertThat(a1.negativeDiagonalMove(1)).isEqualTo(Coordinate.of("a2"));
    }
}

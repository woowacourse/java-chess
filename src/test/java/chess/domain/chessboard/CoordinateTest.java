package chess.domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.*;

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
        assertThatThrownBy(() -> Coordinate.from(alphanumeric))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 좌표값입니다.o9");
    }

    @Test
    void 좌표는_알파벳숫자를_받아_파싱한다() {
        //given
        final String alphanumeric = "a3";

        //when
        final Coordinate coordinate = Coordinate.from(alphanumeric);

        //then
        assertSoftly((softly) -> {
            softly.assertThat(coordinate)
                    .extracting("rankIndex")
                    .isEqualTo('3');
            softly.assertThat(coordinate)
                    .extracting("fileIndex")
                    .isEqualTo('a');
        });
    }

    @Test
    void 좌표는_입력받은_숫자만큼_상하로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");

        //when & then
        assertThat(a1.verticalMove(1)).isEqualTo(Coordinate.from("a2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_좌우로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");

        //when & then
        assertThat(a1.horizontalMove(1)).isEqualTo(Coordinate.from("b1"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_양수인_대각선으로_움직일_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");

        //when & then
        assertThat(a1.positiveDiagonalMove(1)).isEqualTo(Coordinate.from("b2"));
    }

    @Test
    void 좌표는_입력받은_숫자만큼_기울기가_음수인_대각선으로_움직일_수_있다() {
        //given
        final Coordinate b1 = Coordinate.from("b1");

        //when & then
        assertThat(b1.negativeDiagonalMove(1)).isEqualTo(Coordinate.from("a2"));
    }

    @Test
    void 좌표는_다른_좌표와_같은_랭크인지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate b1 = Coordinate.from("b1");

        //when & then
        assertThat(a1.isSameRank(b1)).isTrue();
    }

    @Test
    void 좌표는_다른_좌표와_같은_파일인지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate a2 = Coordinate.from("a2");

        //when & then
        assertThat(a1.isSameFile(a2)).isTrue();
    }

    @Test
    void 좌표는_다른_좌표와의_랭크_차를_계산할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate a5 = Coordinate.from("a5");

        //when & then
        assertThat(a1.calculateRankDistance(a5)).isEqualTo(4);
    }

    @Test
    void 좌표는_다른_좌표와의_파일_차를_계산할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate e1 = Coordinate.from("e1");

        //when & then
        assertThat(a1.calculateFileDistance(e1)).isEqualTo(4);
    }

    @Test
    void 좌표는_다른_좌표가_우상향_대각선에_있는지_검사할_수_있다() {
        //given
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate b2 = Coordinate.from("b2");
        final Coordinate h8 = Coordinate.from("h8");

        //when & then
        assertSoftly((softly) -> {
            softly.assertThat(a1.isPositiveDiagonal(b2)).isTrue();
            softly.assertThat(h8.isPositiveDiagonal(b2)).isTrue();
        });
    }

    @Test
    void 좌표는_다른_좌표와_좌상향_대각선에_있는지_검사할_수_있다() {
        //given
        final Coordinate b6 = Coordinate.from("b6");
        final Coordinate c5 = Coordinate.from("c5");
        final Coordinate e3 = Coordinate.from("e3");

        final Coordinate c1 = Coordinate.from("c1");
        final Coordinate a3 = Coordinate.from("a3");
        //when & then
        assertSoftly((softly) -> {
            assertThat(b6.isNegativeDiagonal(c5)).isTrue();
            assertThat(b6.isNegativeDiagonal(e3)).isTrue();
            assertThat(c1.isNegativeDiagonal(a3)).isTrue();
        });
    }

}

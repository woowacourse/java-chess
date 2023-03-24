package controller.adapter.inward;

import domain.piece.move.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CoordinateAdapterTest {

    @Test
    @DisplayName("좌표가 두 글자가 아니라면 예외가 발생한다")
    void validateLength() {
        String target = "a123";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("X축 좌표가 0 ~ 9 가 아니라면 예외가 발생한다")
    void validateXFormat() {
        String target = "aa";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Y축 좌표가 a ~ z가 아니라면 예외가 발생한다")
    void validateYFormat() {
        String target = "ㅁ1";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Y축 좌표가 알파벳 대문자라면 예외가 발생한다")
    void validateYFormatAlphabetUpperCase() {
        String target = "A1";

        assertThatThrownBy(() -> CoordinateAdapter.convert(target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Y축 좌표가 알파벳 소문자이면서 X축 좌표가 숫자라면 정상 작동한다")
    void validateYFormatAlphabetLowerCase() {
        String target = "c7";
        Coordinate coordinate = CoordinateAdapter.convert(target);

        assertThat(coordinate.getRow())
                .isEqualTo(6);
        assertThat(coordinate.getCol())
                .isEqualTo(2);
    }
}

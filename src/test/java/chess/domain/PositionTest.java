package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("칸 문자열로 위치 생성 시 해당 칸에 맞는 좌표를 갖는다.")
    @Test
    void constructor() {
        Position position = new Position("a1");

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
        assertThat(position.getCharPosition()).isEqualTo("a1");
    }

    @DisplayName("입력 문자열이 두 글자가 아닌 경우 예외가 발생한다.")
    @Test
    void validateLength() {

        assertThatThrownBy(() -> new Position("a11"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 알파벳 소문자 하나와 숫자 하나여야 합니다.");
    }

    @DisplayName("입력 문자열의 첫 글자가 a 부터 h 내에 알파벳이 아닐 경우 예외가 발생한다.")
    @Test
    void validateAlphabetRange() {

        assertThatThrownBy(() -> new Position("i1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알파벳은 a 부터 h 까지만 가능합니다.");
    }

    @DisplayName("입력 문자열의 두번째 글자가 1 부터 8 내에 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void validateNumberRange() {

        assertThatThrownBy(() -> new Position("a9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1 부터 8 까지만 가능합니다.");
    }
}

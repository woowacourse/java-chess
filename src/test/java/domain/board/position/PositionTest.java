package domain.board.position;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a8", "h1", "h8"})
    @DisplayName("파일, 랭크로 주어진 문자열 명령어가 주어지면 그에 맞는 포지션을 반환한다")
    void command(final String command) {
        assertThatCode(() -> Position.from(command)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "x1", "p8", "qwe"})
    @DisplayName("유효하지 않은 문자열 명령어가 주어지면 예외가 발생한다")
    void commandException(final String command) {
        assertThatThrownBy(() -> Position.from(command)).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("wqr")
    void nameTest() {
        final Position position = Position.from("c1");
        System.out.println(position);
    }

}

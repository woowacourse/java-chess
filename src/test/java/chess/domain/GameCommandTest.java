package chess.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameCommandTest {

    @Test
    @DisplayName("게임 커맨드를 생성한다.")
    void construct() {
        assertThatCode(() -> new GameCommand("start"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("정해진 커맨드 외의 게임 커맨드를 입력하면 안된다.")
    void constructThrowException() {
        assertThatThrownBy(() -> new GameCommand("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("적절한 명령어가 아닙니다.");
    }
}

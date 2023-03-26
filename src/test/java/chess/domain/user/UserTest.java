package chess.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @DisplayName("사용자의 이름이 5자보다 길면 예외를 던진다")
    @Test
    void validateLength_throws() {
        assertThatThrownBy(() -> new User("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자 이름은 5자 이하여야합니다.");
    }
}

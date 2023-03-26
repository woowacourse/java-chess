package chess.domain.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    @ParameterizedTest(name = "사용자의 이름이 1~20자 이내가 아니라면 예외가 발생한다.")
    @ValueSource(strings = {"abcdeabcdeabcdeabcdea", ""})
    void create(final String name) {
        assertThatThrownBy(() -> User.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1~20글자 사이여야 합니다.");
    }
}

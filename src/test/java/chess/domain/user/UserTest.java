package chess.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("사용자")
class UserTest {
    @DisplayName("부적절한 이름에 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "!@asdr32"})
    void invalidName(final String invalidName) {
        //given & when & then
        assertThatThrownBy(() -> User.from(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

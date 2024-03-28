package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("이름")
class NameTest {
    @DisplayName("생성에 성공한다")
    @Test
    void create() {
        //given
        String name = "choco";

        //when
        Name made = new Name(name);

        //then
        assertThat(made.getName()).isEqualTo(name);
    }

    @DisplayName("부적절한 이름 생성 시 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "qwertyuiopasdfghj"})
    void invalidName(String invalidName) {
        //given & when & then
        assertThatThrownBy(() -> new Name(invalidName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

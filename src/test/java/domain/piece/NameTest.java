package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("기물의 이름을 생성한다.")
    @Test
    void name_generate() {
        Name name = Name.of("a", false);
        assertThat(name).isEqualTo(Name.of("a", false));
    }

    @DisplayName("기물의 이름이 2자리 이상 혹은 공백일 경우 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"abc", ""})
    void invalid_name_generate(String value) {
        assertThatThrownBy(() -> Name.of(value, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물의 이름은 한자리만 가능합니다.");
    }

    @DisplayName("기물의 이름이 공백인 경우 미리 캐싱해둔 EMPTY가 반환된다.")
    @Test
    void create_empty_name_if_input_blank() {
        assertThat(Name.of(".", true)).isEqualTo(Name.EMPTY);
    }
}
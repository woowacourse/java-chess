package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Rank(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("행은 1부터 8사이의 값만 허용한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void checkValue(int source) {
        assertThatThrownBy(() -> new Rank(source))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

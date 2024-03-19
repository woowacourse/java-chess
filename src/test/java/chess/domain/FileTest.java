package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "h"})
    @DisplayName("a부터 h까지의 가로 위치를 가진다.")
    void createRank(String position) {
        File file = new File(position);

        assertThat(file).isEqualTo(new File(position));
    }

    @ParameterizedTest
    @ValueSource(strings = {"z", "i", "ab"})
    @DisplayName("가로 위치가 a 부터 h 사이의 값이 아니라면 예외가 발생한다.")
    void invalidRank(String position) {
        assertThatThrownBy(() -> new File(position))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
package chess.domain.point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @ParameterizedTest
    @ValueSource(chars = {'a', 'h'})
    @DisplayName("a부터 h까지의 가로 위치를 가진다.")
    void createRank(char position) {
        File file = File.of(position);

        assertThat(file).isEqualTo(File.of(position));
    }

    @ParameterizedTest
    @ValueSource(chars = {'z', 'i'})
    @DisplayName("가로 위치가 a 부터 h 사이의 값이 아니라면 예외가 발생한다.")
    void invalidRank(char position) {
        assertThatThrownBy(() -> File.of(position))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

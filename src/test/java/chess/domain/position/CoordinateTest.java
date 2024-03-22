package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinateTest {

    @ParameterizedTest
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    @DisplayName("좌표에 해당하는 File을 찾을 수 있다")
    void should_find_file(char fileName) {
        Coordinate coordinate = Coordinate.of(fileName + "1");

        assertThat(coordinate.file()).isEqualTo(File.of(fileName));
    }

    @ParameterizedTest
    @ValueSource(chars = {'1', '2', '3', '4', '5', '6', '7', '8'})
    @DisplayName("좌표에 해당하는 Rank를 찾을 수 있다")
    void should_find_rank(char rankName) {
        Coordinate coordinate = Coordinate.of("a" + rankName);

        assertThat(coordinate.rank()).isEqualTo(Rank.of(rankName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"i0", "z9"})
    @DisplayName("올바르지 않은 좌표 객체를 만들 수 없다")
    void should_find_rank(String coordinateName) {
        assertThatThrownBy(() -> Coordinate.of(coordinateName))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

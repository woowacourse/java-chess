package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SquareTest {

    @Test
    @DisplayName("칸 캐싱했는지 확인")
    void checkSquareCache() {
        assertThat(Square.of("a1")).isEqualTo(Square.of("a1"));
        assertThat(Square.of("a1")).isNotEqualTo(Square.of("a2"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a9", "f0", "jkl", "j3"})
    @DisplayName("잘못된 값이 of에 들어갔을 때 예외 발생")
    void validLocation(String location) {
        assertThatThrownBy(() -> Square.of(location))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못");
    }

    @Test
    @DisplayName("주어진 Square 에서 file, rank 이동한 Square 구하기")
    void moveTo() {
        assertThat(Square.moveTo(-1, 1, Square.of("c3"))).isEqualTo(Square.of("b4"));
        assertThat(Square.moveTo(-3, 1, Square.of("c3"))).isEqualTo(Square.of("c3"));
    }
}

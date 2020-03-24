package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @ParameterizedTest
    @ValueSource(strings = {"b1", "B1"})
    @DisplayName("Point 생성")
    void create(String point) {
        assertThat(Point.from(point)).isInstanceOf(Point.class);
    }

    @Test
    void createWithInvalidName() {
        assertThatThrownBy(() -> {
            Point.from("z1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(".+는 잘못된 입력입니다.");
    }
}
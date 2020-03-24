package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"b1", "B1"})
    @DisplayName("Point 생성")
    void createFrom(String point) {
        assertThat(Position.from(point)).isInstanceOf(Position.class);
    }

    @Test
    void createOf() {
        assertThat(Position.of(Column.C, Row.SEVEN)).isEqualTo(Position.from("C7"));
    }

    @Test
    @DisplayName("잘못된 Point 인자")
    void createWithInvalidName() {
        assertThatThrownBy(() -> {
            Position.from("z1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(".+는 잘못된 입력입니다.");
    }
}
package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(strings = {"b1", "B1"})
    @DisplayName("from 생성")
    void createFrom(String point) {
        assertThat(Position.from(point)).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("of 생성")
    void createOf() {
        assertThat(Position.of(File.C, Rank.SEVEN)).isEqualTo(Position.from("C7"));
    }

    @Test
    @DisplayName("잘못된 Point 인자")
    void createWithInvalidName() {
        assertThatThrownBy(() -> {
            Position.from("z1");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching(".+는 잘못된 입력입니다.");
    }

    @Test
    @DisplayName("점 대칭")
    void opposite() {
        assertThat(Position.from("b1").opposite()).isEqualTo(Position.from("g8"));
    }

    @Test
    @DisplayName("Position 전체 list 크기")
    void list() {
        assertThat(Position.list()).hasSize(64);
    }

    @Test
    @DisplayName("가로축 기준 대칭")
    void horizontalFlip() {
        assertThat(Position.from("b1").horizontalFlip()).isEqualTo(Position.from("b8"));
    }
}
package chess.domain.attribute;

import static chess.domain.attribute.Rank.EIGHT;
import static chess.domain.attribute.Rank.FOUR;
import static chess.domain.attribute.Rank.ONE;
import static chess.domain.attribute.Rank.THREE;
import static chess.domain.attribute.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {
    @DisplayName("1~8 범위를 벗어나는 행 번호를 입력하면 예외가 발생한다.")
    @ValueSource(ints = {0, 9})
    @ParameterizedTest
    void constructor(int row) {
        assertThatThrownBy(() -> Rank.of(row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("랭크는 1~8 사이로 입력해주세요: " + row);
    }

    @DisplayName("현재 칸의 위칸을 반환한다.")
    @Test
    void up() {
        assertThat(THREE.up()).isEqualTo(FOUR);
    }

    @DisplayName("현재 칸의 아래칸을 반환한다.")
    @Test
    void down() {
        assertThat(THREE.down()).isEqualTo(TWO);
    }

    @DisplayName("위칸이 존재하지 않으면 예외를 발생시킨다.")
    @Test
    void upException() {
        assertThatThrownBy(EIGHT::up)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("아래 칸이 존재하지 않으면 예외를 발생시킨다.")
    @Test
    void downException() {
        assertThatThrownBy(ONE::down)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("위 칸으로 이동할 수 있는지 알려준다.")
    @Test
    void canMoveUp() {
        assertAll(
                () -> assertTrue(ONE::canMoveUp),
                () -> assertFalse(EIGHT::canMoveUp)
        );
    }

    @DisplayName("아래 칸으로 이동할 수 있는지 알려준다.")
    @Test
    void canMoveDown() {
        assertAll(
                () -> assertTrue(EIGHT::canMoveDown),
                () -> assertFalse(ONE::canMoveDown)
        );
    }
}

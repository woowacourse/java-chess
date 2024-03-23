package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RankTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> Rank.from(1))
                .doesNotThrowAnyException();
    }

    @DisplayName("행은 1부터 8사이의 값만 허용한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void checkValue(int source) {
        assertThatThrownBy(() -> Rank.from(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가중치 만큼 움직일 수 있는지 확인한다.")
    @Test
    void canMove() {
        assertAll(
                () -> assertTrue(Rank.SEVEN.canMove(1)),
                () -> assertFalse(Rank.SEVEN.canMove(2))
        );
    }

    @DisplayName("가중치 만큼 움직일 수 있다.")
    @Test
    void move() {
        assertThat(File.A.move(7)).isEqualTo(File.H);
    }

    @DisplayName("다른 Rank와 비교할 수 있다.")
    @Nested
    class compare {
        @DisplayName("더 큰 Rank와 비교하면 -1을 반환한다.")
        @Test
        void bigger() {
            File file = File.D;
            assertThat(file.compare(File.H)).isEqualTo(-1);
        }

        @DisplayName("같은 Rank와 비교하면 0을 반환한다.")
        @Test
        void same() {
            File file = File.D;
            assertThat(file.compare(File.D)).isEqualTo(0);
        }

        @DisplayName("작은 Rank와 비교하면 1을 반환한다.")
        @Test
        void less() {
            File file = File.D;
            assertThat(file.compare(File.A)).isEqualTo(1);
        }
    }
}

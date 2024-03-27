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

class FileTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> File.from('a'))
                .doesNotThrowAnyException();
    }

    @DisplayName("열은 소문자 a부터 h까지의 값만 허용한다.")
    @ParameterizedTest
    @ValueSource(chars = {'A', 'i'})
    void checkValue(char source) {
        assertThatThrownBy(() -> File.from(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("가중치 만큼 움직일 수 있는지 확인한다.")
    @Test
    void canMove() {
        assertAll(
                () -> assertTrue(File.G.canMove(1)),
                () -> assertFalse(File.G.canMove(2))
        );
    }


    @DisplayName("가중치 만큼 움직일 수 있다.")
    @Test
    void move() {
        assertThat(File.A.move(7)).isEqualTo(File.H);
    }

    @DisplayName("다른 File과 비교할 수 있다.")
    @Nested
    class compare {
        @DisplayName("더 큰 File과 비교하면 -1을 반환한다.")
        @Test
        void bigger() {
            File file = File.D;
            assertThat(file.compare(File.H)).isEqualTo(-1);
        }

        @DisplayName("같은 File과 비교하면 0을 반환한다.")
        @Test
        void same() {
            File file = File.D;
            assertThat(file.compare(File.D)).isEqualTo(0);
        }

        @DisplayName("작은 File과 비교하면 1을 반환한다.")
        @Test
        void less() {
            File file = File.D;
            assertThat(file.compare(File.A)).isEqualTo(1);
        }
    }
}

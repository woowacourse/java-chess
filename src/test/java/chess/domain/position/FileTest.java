package chess.domain.position;

import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {

    // right
    @Test
    @DisplayName("한칸 오른쪽으로 이동한다.")
    void right() {
        assertThat(A.right()).isEqualTo(B);
    }

    @Test
    @DisplayName("file가 H인 경우 한칸 오른쪽으로 이동이 불가능하다.")
    void rightHFile() {
        assertThatThrownBy(H::right)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, B", "2, C", "3, D", "4, E",
            "5, F", "6, G", "7, H"})
    @DisplayName("현재 file에서 해당하는 숫자만큼 오른쪽로 이동이 가능하다.")
    void right(final int step, final File file) {
        assertThat(A.right(step)).isEqualTo(file);
    }

    @Test
    @DisplayName("현재 file에서 숫자만큼 오른쪽으로 이동하여, H을 초과한 경우 예외가 발생한다.")
    void canNotRightOverH() {
        assertThatThrownBy(() -> A.right(8))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"B", "C", "D", "E", "F", "G"})
    @DisplayName("file가 B ~ H인 경우 오른쪽으로 한칸 이동할 수 있다.")
    void canRight(final File file) {
        assertThat(file.canRight()).isTrue();
    }

    @Test
    @DisplayName("file가 H인 경우 오른쪽으로 한칸 이동할 수 없다.")
    void canRight() {
        assertThat(H.canRight()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5", "6", "7"})
    @DisplayName("현재 file에서 해당하는 숫자만큼 오른쪽으로 이동이 가능한지 여부를 반환힌다.")
    void canRight(final int step) {
        assertAll(
                () -> assertThat(A.canRight(step)).isTrue(),
                () -> assertThat(A.canRight(8)).isFalse()
        );
    }

    // left
    @Test
    @DisplayName("한칸 왼쪽으로 이동한다.")
    void left() {
        assertThat(H.left()).isEqualTo(G);
    }

    @Test
    @DisplayName("file가 A인 경우 한칸 왼쪽으로 이동이 불가능하다.")
    void leftOneFile() {
        assertThatThrownBy(A::left)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"1, G", "2, F", "3, E", "4, D",
            "5, C", "6, B", "7, A"})
    @DisplayName("현재 file에서 해당하는 숫자만큼 왼쪽으로 이동이 가능하다.")
    void left(final int step, final File file) {
        assertThat(H.left(step)).isEqualTo(file);
    }


    @Test
    @DisplayName("현재 file에서 숫자만큼 왼쪽으로 이동하여, 1을 미만이 된 경우 예외가 발생한다.")
    void canNotLeftUnderOne() {
        assertThatThrownBy(() -> H.left(8))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동이 불가능합니다.");
    }

    @ParameterizedTest
    @CsvSource({"H", "G", "F", "E", "D", "C", "B"})
    @DisplayName("file가 2 ~ 8인 경우 왼쪽으로 한칸 이동할 수 있다.")
    void canLeft(final File file) {
        assertThat(file.canLeft()).isTrue();
    }

    @Test
    @DisplayName("file가 A인 경우 왼쪽으로 한칸 이동할 수 없다.")
    void canLeft() {
        assertThat(A.canLeft()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"1", "2", "3", "4", "5", "6", "7"})
    @DisplayName("현재 file에서 해당하는 숫자만큼 왼쪽으로 이동이 가능한지 여부를 반환힌다.")
    void canLeft(final int step) {
        assertAll(
                () -> assertThat(H.canLeft(step)).isTrue(),
                () -> assertThat(H.canLeft(8)).isFalse()
        );
    }

    // ---
    @ParameterizedTest
    @CsvSource({"A", "C"})
    @DisplayName("두 file간의 거리가 한칸인 경우 참을 반환한다.")
    void canMoveOneSpace(final File file) {
        assertThat(B.canMoveOneSpace(file)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"D", "E", "F", "G", "H"})
    @DisplayName("두 file간의 거리가 한칸이 아닌 경우 거짓을 반환한다.")
    void canNotMoveOneSpace(final File file) {
        assertThat(B.canMoveOneSpace(file)).isFalse();
    }
}

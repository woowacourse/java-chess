package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @Test
    @DisplayName("파일 이름으로 File을 구한다.")
    void from() {
        // given
        String name = "a";

        // when
        File actualFile = File.from(name);

        // then
        assertThat(actualFile).isEqualTo(File.A);
    }

    @Test
    @DisplayName("존재하지 않는 File 이름으로 조회할 경우 예외가 발생한다.")
    void fromWithInvalidName() {
        // given
        String name = "q";

        // when & then
        assertThatThrownBy(() -> File.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("File의 차이(Difference)를 계산한다.")
    void minus() {
        // given
        File target = File.A;
        File other = File.D;

        // when
        Difference difference = target.minus(other);

        // then
        assertThat(difference).isEqualTo(Difference.from(-3));
    }

    @Test
    @DisplayName("오프셋으로 다음 File을 구한다.")
    void calculateNextFile() {
        // given
        File file = File.A;
        int offset = 2;

        // when
        File actualFile = file.calculateNextFile(offset);

        // then
        assertThat(actualFile).isEqualTo(File.C);
    }

    @Test
    @DisplayName("오프셋으로 다음 File을 구할 때 해당 좌표의 File이 없다면 예외가 발생한다.")
    void calculateNextFileWhenNotExist() {
        // given
        File file = File.H;
        int offset = 1;

        // when & then
        assertThatThrownBy(() -> file.calculateNextFile(offset))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

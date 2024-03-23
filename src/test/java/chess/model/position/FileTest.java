package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        File rank = File.A;
        int offset = 2;

        // when
        File actualFile = rank.calculateNextFile(offset);

        // then
        assertThat(actualFile).isEqualTo(File.C);
    }
}

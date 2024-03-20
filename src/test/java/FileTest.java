import domain.piece.attribute.point.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FileTest {
    @Test
    @DisplayName("다음 파일을 가져온다.")
    void get_next_file() {
        File file = File.F;

        final var sut = file.next();

        assertThat(sut).isEqualTo(File.G);
    }

    @Test
    @DisplayName("그전 파일을 가져온다.")
    void get_prev_file() {
        File file = File.D;

        final var sut = file.prev();

        assertThat(sut).isEqualTo(File.C);
    }
}

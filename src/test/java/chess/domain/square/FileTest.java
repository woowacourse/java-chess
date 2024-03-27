package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class FileTest {

    @DisplayName("좌표가 유효하지 않을 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfFileIsInvalid() {
        assertThatCode(() -> File.findByValue("i"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 파일 간 거리를 구한다.")
    @Test
    void calculateDistanceBetweenTwoFiles() {
        final File file = File.a;
        final File other = File.h;

        final int actual = file.calculateDistance(other);

        assertThat(actual).isEqualTo(7);
    }

    @DisplayName("두 파일 간 경로를 찾는다.")
    @Test
    void findPathBetweenTwoFiles() {
        final File file = File.e;
        final File other = File.h;

        final List<File> actual = file.findFilePath(other);

        assertThat(actual).containsExactlyInAnyOrder(File.f, File.g);
    }
}

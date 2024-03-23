package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

@DisplayName("파일")
class FileTest {

    @DisplayName("좌표가 유효하지 않을 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfFileIsInvalid() {
        assertThatCode(() -> File.from("i"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("같은 파일인지 확인한다.")
    @ParameterizedTest
    @CsvSource({"a, true", "b, false"})
    void checkIsSameFile(String other, boolean expected) {
        File file = File.a;

        boolean actual = file.isSameFile(File.from(other));

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("두 파일 간 거리를 구한다.")
    @Test
    void calculateDistanceBetweenTwoFiles() {
        File file = File.a;
        File other = File.h;

        int actual = file.calculateDistance(other);

        assertThat(actual).isEqualTo(7);
    }

    @DisplayName("두 파일 간 경로를 찾는다.")
    @Test
    void findPathBetweenTwoFiles() {
        File file = File.e;
        File other = File.h;

        List<File> actual = file.findFilePath(other);

        assertThat(actual).containsExactlyInAnyOrder(File.f, File.g);
    }
}

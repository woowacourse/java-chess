package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {
    @DisplayName("현재 칸과 이동할 칸의 File 차를 계산한다.")
    @ParameterizedTest(name = "현재 file: {0}, 이동할 file: {1} => 차: {2}")
    @CsvSource({"a,b,-1", "a,h,-7", "c,a,2"})
    void calculateFileInterval_success(String src, String dst, int interval) {
        assertThat(File.calculate(File.findFileBy(src), File.findFileBy(dst))).isEqualTo(interval);
    }

    @DisplayName("File의 범위를 벗어나면 예외가 발생한다.")
    @ParameterizedTest(name = "source: {0}, destination: {1}")
    @CsvSource({"a,i", "A,B"})
    void calculateFileInterval_fail(String src, String dst) {
        assertThatThrownBy(() -> File.calculate(File.findFileBy(src), File.findFileBy(dst)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File을 입력했습니다.");
    }

    @DisplayName("fileInput으로 File을 찾는다.")
    @ParameterizedTest(name = "fileInput: {0}, File: {1}")
    @CsvSource({"a,A", "c,C"})
    void findFileByFileInput_success(String fileInput, File file) {
        assertThat(File.findFileBy(fileInput)).isEqualTo(file);
    }

    @DisplayName("fileInput이 File에 존재하지 않는다면 예외가 발생한다.")
    @ParameterizedTest(name = "존재하지 않는 file: {0}")
    @CsvSource({"j", "A"})
    void findFileByFileInput_fail(String fileInput) {
        assertThatThrownBy(() -> File.findFileBy(fileInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File을 입력했습니다.");
    }

    @DisplayName("방향을 이용하여 다음 File을 반환한다.")
    @ParameterizedTest(name = "방향: {0}, 현재 file: {1}, 이동할 file: {2}")
    @CsvSource({"2,A,C", "-1,G,F"})
    void nextFile(int direction, File before, File next) {
        assertThat(before.next(direction)).isEqualTo(next);
    }
}

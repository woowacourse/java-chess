package chess.domain.square;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {
    @ParameterizedTest(name = "현재 칸과 이동할 칸의 File 차를 계산한다.")
    @CsvSource({"a,b,-1", "a,h,-7", "c,a,2"})
    void calculateFileInterval_success(String src, String dst, int interval) {
        assertThat(File.calculate(File.findFileBy(src), File.findFileBy(dst))).isEqualTo(interval);
    }

    @ParameterizedTest(name = "File의 범위를 벗어나면 예외가 발생한다.")
    @CsvSource({"a,i", "A,B"})
    void calculateFileInterval_fail(String src, String dst) {
        assertThatThrownBy(() -> File.calculate(File.findFileBy(src), File.findFileBy(dst)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File을 입력했습니다.");
    }

    @ParameterizedTest(name = "fileInput으로 File을 찾는다.")
    @CsvSource({"a,A", "c,C"})
    void findFileByFileInput_success(String fileInput, File file) {
        assertThat(File.findFileBy(fileInput)).isEqualTo(file);
    }

    @ParameterizedTest(name = "fileInput이 File에 존재하지 않는다면 예외가 발생한다.")
    @CsvSource({"j", "A"})
    void findFileByFileInput_fail(String fileInput) {
        assertThatThrownBy(() -> File.findFileBy(fileInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File을 입력했습니다.");
    }

    @ParameterizedTest(name = "방향을 이용하여 다음 File을 반환한다.")
    @CsvSource({"2,A,C", "-1,G,F"})
    void nextFile(int direction, File before, File next) {
        assertThat(before.next(direction)).isEqualTo(next);
    }
}

package chess.domain.square;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {
    @ParameterizedTest(name = "현재 칸과 이동할 칸의 File 차를 계산한다.")
    @CsvSource({"a,b,-1", "a,h,-7"})
    void calculateFileInterval(String src, String dst, int interval) {
        assertThat(File.calculate(File.findFileBy(src), File.findFileBy(dst))).isEqualTo(interval);
    }

    @ParameterizedTest(name = "File의 범위를 벗어나면 에러가 발생한다.")
    @CsvSource({"a,i", "A,B"})
    void calculateFileInterval_fail(String src, String dst) {
        assertThatThrownBy(() -> File.calculate(File.findFileBy(src), File.findFileBy(dst)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 File을 입력했습니다.");
    }
}

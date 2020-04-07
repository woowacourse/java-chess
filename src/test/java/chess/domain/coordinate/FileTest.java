package chess.domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileTest {
    @DisplayName("정상 범위의 경우")
    @ParameterizedTest
    @CsvSource(value = {"1,A", "2,B", "3,C", "4,D", "5,E", "6,F", "7,G", "8,H"})
    void findByValue2(int file, File expect) {
        File actual = File.findByValue(file);
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("1~8 을 벗어나는 경우 Exception 발생")
    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    void findByValue(int value) {
        assertThatThrownBy(() -> File.findByValue(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("file : %d, file의 value는 1부터 8까지 입니다.", value);
    }
}

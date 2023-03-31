package chess.domain.square;

import static chess.domain.square.File.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {

    @Test
    @DisplayName("a~h가 아닌 File을 검색하면 예외가 발생한다")
    void search_file_exception() {
        assertThatThrownBy(() -> from('i'))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("a~h가 File를 검색하면 Enum 파일을 반환한다.")
    void search_file() {
        assertThat(from('a')).isEqualTo(File.A);
        assertThat(from('h')).isEqualTo(File.H);
    }

    @ParameterizedTest
    @CsvSource({"a,0", "b,1", "c,2", "d,3", "e,4", "f,5", "g,6", "h,7"})
    @DisplayName("fileNumber는 'a' -> 0, 'b' -> 1, ... 'h' -> 7을 리턴한다.")
    void get_file_number(char file, int fileNumber) {
        assertThat(from(file).getFileNumber()).isEqualTo(fileNumber);
    }
}

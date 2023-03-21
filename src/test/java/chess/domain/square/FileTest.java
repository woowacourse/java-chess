package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    @DisplayName("a~h가 아닌 File을 검색하면 예외가 발생한다")
    void search_file_exception() {
        assertThatThrownBy(() -> File.from('i'))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("a~h가 File를 검색하면 Enum 파일을 반환한다.")
    void search_file() {
        assertThat(File.from('a')).isEqualTo(File.A);
        assertThat(File.from('h')).isEqualTo(File.H);
    }

}

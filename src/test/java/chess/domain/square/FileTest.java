package chess.domain.square;

import chess.domain.exception.FileNotFoundException;
import chess.domain.square.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    @Test
    @DisplayName("a~h가 아닌 File을 검색하면 예외가 발생한다")
    void search_file_exception() {
        assertThatThrownBy(() -> File.from('i'))
                .isInstanceOf(FileNotFoundException.class);
    }

    @Test
    @DisplayName("a~h가 File를 검색하면 Enum 파일을 반환한다.")
    void search_file() {
        assertThat(File.from('a')).isEqualTo(File.A);
        assertThat(File.from('h')).isEqualTo(File.H);
    }

}
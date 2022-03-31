package chess.model;

import static chess.model.File.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {

    @ParameterizedTest
    @CsvSource(value = {"A:false", "B:true", "D:false", "E:false"}, delimiter = ':')
    @DisplayName("해당 File이 두 File 사이에 존재하는지 검증한다.")
    void traceGroup(File file, boolean expected) {
        //given
        List<File> files = A.betweenFiles(D);

        //then
        assertThat(files.contains(file)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a:A", "b:B", "c:C", "d:D", "e:E", "f:F", "g:G", "h:H"}, delimiter = ':')
    @DisplayName("문자열로부터 생성을 검증한다.")
    void of(String input, File file) {
        assertThat(File.of(input)).isEqualTo(file);
    }
}

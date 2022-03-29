package chess.model;

import static chess.model.File.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FileTest {

    @ParameterizedTest
    @CsvSource(value = {"A:false", "B:true", "D:false", "E:false"}, delimiter = ':')
    @DisplayName("해당 File이 두 File 사이에 존재하는지 검증한다.")
    void traceGroup(File file, boolean expected) {
        //given
        boolean actual = file.isBetween(A, D);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}

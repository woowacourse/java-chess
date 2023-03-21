package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FileTest {

    @ParameterizedTest(name = "value={0} 가 주어지면 file={1} 반환된다.")
    @CsvSource({"A,A", "B,B", "C,C", "D,D", "E,E", "F,F", "G,G", "H,H"})
    void findFile_givenStringValue_thenReturnFile(final String value, final File file) {
        // when, then
        assertThat(File.findFile(value)).isSameAs(file);
    }

    @ParameterizedTest(name = "value={0} 가 주어지면 file={1} 반환된다.")
    @CsvSource({"1,A", "2,B", "3,C", "4,D", "5,E", "6,F", "7,G", "8,H"})
    void findFile_givenIntValue_thenReturnFile(final int value, final File file) {
        // when, then
        assertThat(File.findFile(value)).isSameAs(file);
    }
}

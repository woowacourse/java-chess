package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "1", "12", "ab", "z"})
    @DisplayName("유효하지 않는 좌표로 File를 생성하면 예외가 발생한다.")
    void from(String given) {
        //when //then
        assertThatThrownBy(() -> File.from(given))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"A,C,-2", "H,E,3", "C,F,-3"})
    @DisplayName("두 File의 차이를 구한다.")
    void minus(File given, File other, int expected) {
        //when
        int result = given.minus(other);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"B,2,D", "A,3,D", "H,-5,C"})
    @DisplayName("이동할 칸 수 만큼 증가한 랭크를 반환한다.")
    void findNextFile(File given, int offset, File expected) {
        //when
        File result = given.findNextFile(offset);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"B,-6", "A,20", "H,1"})
    @DisplayName("이동할 수 없다면 예외가 발생한다.")
    void findNextFileExceedRange(File given, int offset) {
        //when //then
        assertThatThrownBy(() -> given.findNextFile(offset))
                .isInstanceOf(IllegalStateException.class);
    }
}

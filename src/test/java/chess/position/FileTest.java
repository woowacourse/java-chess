package chess.position;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class FileTest {

    @ParameterizedTest
    @ValueSource(strings = {"i", "z", "1"})
    @NullAndEmptySource
    @DisplayName("올바르지 않은 열은 변환하는 경우 예외를 발생한다.")
    void invalidFileNameTest(String fileName) {
        assertThatThrownBy(() -> File.from(fileName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 열 번호입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("범위를 넘어가도록 열을 계산하는 경우 예외를 발생한다.")
    void fileOverflowTest(int difference) {
        File file = File.from("a");
        assertThatThrownBy(() -> file.createFileByDifferenceOf(difference))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("열 범위를 벗어납니다.");
    }
}

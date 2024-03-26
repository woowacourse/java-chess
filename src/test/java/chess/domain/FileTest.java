package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class FileTest {

    @ParameterizedTest
    @DisplayName("주어진 숫자만큼 이동한 파일을 반환한다.")
    @CsvSource(value = {"A:1:B", "B:-1:A"}, delimiter = ':')
    void moveByOffset(File before, int offset, File expectedAfter) {
        File actualAfter = before.moveByOffset(offset);

        assertThat(actualAfter).isEqualTo(expectedAfter);
    }

    @ParameterizedTest
    @DisplayName("범위를 넘으면 예외가 발생한다.")
    @CsvSource(value = {"A:-1", "H:1"}, delimiter = ':')
    void moveByOffsetFail(File file, int offset) {
        assertThatCode(() -> file.moveByOffset(offset))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판 가로 범위를 넘어갔습니다.");
    }
}

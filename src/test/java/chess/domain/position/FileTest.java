package chess.domain.position;

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
                .hasMessage("잘못된 열 번호입니다.");
    }
}

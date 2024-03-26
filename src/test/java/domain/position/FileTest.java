package domain.position;

import domain.board.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @DisplayName("인덱스를 입력하면 해당하는 File을 반환한다.")
    @Test
    void getFileTest() {
        // Given
        final int index = 2;

        // When
        File file = File.of(index);

        // Then
        assertThat(file).isEqualTo(File.C);
    }

    @DisplayName("유효하지 않은 인덱스를 입력하면 예외를 발생시킨다.")
    @Test
    void throwExceptionWhenInputInvalidIndex() {
        // Given
        final int index = 13;

        // When & Then
        assertThatThrownBy(() -> File.of(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 인덱스입니다.");
    }
}

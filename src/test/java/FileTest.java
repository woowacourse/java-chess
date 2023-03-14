import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileTest {

    @DisplayName("text에 맞는 File 인스턴스를 반환한다.")
    @Test
    void shouldReturnAppropriateFileWhenIncomeText() {
        File file = File.from("a");
        assertThat(file).isEqualTo(File.A);
    }

    @DisplayName("유효하지 않은 File 텍스트 입력 시 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenInvalidFileText() {
        assertThatThrownBy(() -> File.from("q"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 file입니다.");
    }
}
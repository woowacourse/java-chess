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

    @DisplayName("source file과 target file의 거리 차이를 반환한다.")
    @Test
    void shouldReturnDifferenceBetweenSourceAndTargetWhenInputTargetToSource() {
        File sourceFile = File.from("d");
        File targetFile = File.from("g");
        assertThat(sourceFile.calculateIncrement(targetFile)).isEqualTo(3);
    }

    @DisplayName("source file의 오른쪽 값을 반환한다.")
    @Test
    void shouldReturnNextFileWhenRequest() {
        assertThat(File.B.getNext()).isEqualTo(File.C);
    }

    @DisplayName("file이 h에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastFileRequestGetNext() {
        assertThatThrownBy(() -> File.H.getNext())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인덱스를 벗어난 움직임입니다.");
    }

    @DisplayName("source file의 왼쪽 값을 반환한다.")
    @Test
    void shouldReturnPreviousFileWhenRequest() {
        assertThat(File.B.getPrevious()).isEqualTo(File.A);
    }

    @DisplayName("file이 h에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstFileRequestGetPrevious() {
        assertThatThrownBy(() -> File.A.getPrevious())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인덱스를 벗어난 움직임입니다.");
    }
}
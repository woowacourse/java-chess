package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @DisplayName("사용자 입력 텍스트에 맞는 File을 반환한다.")
    @Test
    void shouldReturnAppropriateFileWhenInputText() {
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

    @DisplayName("Source file과 Target file의 거리 차이를 반환한다.")
    @Test
    void shouldReturnDistanceBetweenSourceAndTargetWhenInput() {
        File sourceFile = File.from("d");
        File targetFile = File.from("g");
        assertThat(sourceFile.calculateIncrement(targetFile)).isEqualTo(3);
    }

    @DisplayName("Source file의 오른쪽 값을 반환한다.")
    @Test
    void shouldReturnNextFileWhenRequest() {
        assertThat(File.B.getNext()).isEqualTo(File.C);
    }

    @DisplayName("File이 H에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastFileRequestGetNext() {
        assertThatThrownBy(() -> File.H.getNext())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 다음 File은 존재하지 않습니다.");
    }

    @DisplayName("Source file의 왼쪽 값을 반환한다.")
    @Test
    void shouldReturnPreviousFileWhenRequest() {
        assertThat(File.B.getPrevious()).isEqualTo(File.A);
    }

    @DisplayName("File이 H에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstFileRequestGetPrevious() {
        assertThatThrownBy(() -> File.A.getPrevious())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("서버 내부 에러 - 이전 File은 존재하지 않습니다.");
    }
}

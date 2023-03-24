package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("File은")
public class FileTest {

    @DisplayName("D와 G 사이 거리 차이로 3을 반환한다.")
    @Test
    void shouldReturnDistanceBetweenSourceAndTargetWhenInput() {
        File sourceFile = File.D;
        File targetFile = File.G;
        assertThat(sourceFile.calculateIncrement(targetFile)).isEqualTo(3);
    }

    @DisplayName("B의 다음 값으로 C를 반환한다.")
    @Test
    void shouldReturnNextFileWhenRequest() {
        assertThat(File.B.getNext()).isEqualTo(File.C);
    }

    @DisplayName("H에서 다음 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenLastFileRequestGetNext() {
        assertThatThrownBy(() -> File.H.getNext())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - 다음 File은 존재하지 않습니다.");
    }

    @DisplayName("B의 이전 값으로 A를 반환한다.")
    @Test
    void shouldReturnPreviousFileWhenRequest() {
        assertThat(File.B.getPrevious()).isEqualTo(File.A);
    }

    @DisplayName("A에서 이전 위치를 가져오려고 하면 예외가 발생한다.")
    @Test
    void shouldThrowExceptionWhenFirstFileRequestGetPrevious() {
        assertThatThrownBy(() -> File.A.getPrevious())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("서버 내부 에러 - 이전 File은 존재하지 않습니다.");
    }
}

package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.board.File.A;
import static chess.domain.board.File.H;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileTest {

    @Test
    @DisplayName("한칸 왼쪽의 파일을 반환한다")
    void fileMinusTest() {
        final var file = File.C.minus();

        assertThat(file).isEqualTo(File.B);
    }

    @Test
    @DisplayName("한칸 오른쪽의 파일을 반환한다")
    void filePlusTest() {
        final var file = File.C.plus();

        assertThat(file).isEqualTo(File.D);
    }

    @Test
    @DisplayName("범위를 벗어난 파일로 이동하면 예외가 발생한다")
    void filePlusExceptionTest() {
        assertThatThrownBy(H::plus)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어난 파일로 이동하면 예외가 발생한다")
    void fileMinusExceptionTest() {
        assertThatThrownBy(A::minus)
                .isInstanceOf(IllegalArgumentException.class);
    }

}

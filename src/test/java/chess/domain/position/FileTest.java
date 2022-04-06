package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    @DisplayName("File에 A~F가 포함되는지 확인한다.")
    void contain() {
        assertThat(File.values())
                .containsOnly(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H);
    }

    @Test
    @DisplayName("값을 이용해 File을 찾는다.")
    void findFile() {
        assertThat(File.from(3)).isEqualTo(File.C);
    }

    @Test
    @DisplayName("a~h 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> File.from(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 File 값 입니다.");
    }

    @Test
    @DisplayName("File을 1 증가시킨다.")
    void plus() {
        File file = File.from(4);
        assertThat(file.move(1)).isEqualTo(File.E);
    }

    @Test
    @DisplayName("File을 1 감소시킨다.")
    void minus() {
        File file = File.from(2);
        assertThat(file.move(-1)).isEqualTo(File.A);
    }

}

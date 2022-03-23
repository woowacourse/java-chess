package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @Test
    @DisplayName("File에 a~h이 포함되는지 확인한다.")
    void contain() {
        assertThat(File.values())
            .containsOnly(File.A, File.B, File.C, File.D, File.E, File.F, File.G, File.H);
    }

    @Test
    @DisplayName("FIle에 a~h값이 있는지 확인한다.")
    void containValue() {
        assertThat(File.A.getValue()).isEqualTo("a");
    }

    @Test
    @DisplayName("값을 이용해 File을 찾는다.")
    void findFile() {
        assertThat(File.of("c")).isEqualTo(File.C);
    }

    @Test
    @DisplayName("a~h 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> File.of("i"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 존재하지 않는 File 값 입니다.");
    }

}

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
    @DisplayName("값을 이용해 File을 찾는다.")
    void findFile() {
        assertThat(File.from('c')).isEqualTo(File.C);
    }

    @Test
    @DisplayName("a~h 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> File.from('i'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 File 값 입니다.");
    }

    @Test
    @DisplayName("File을 1 증가시킨다.")
    void plus() {
        File file = File.from('b');
        assertThat(file.move(1)).isEqualTo(File.C);
    }

    @Test
    @DisplayName("File을 1 감소시킨다.")
    void minus() {
        File file = File.from('b');
        assertThat(file.move(-1)).isEqualTo(File.A);
    }

    @Test
    @DisplayName("File을 1 증가 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void plusOutOfBounds() {
        File file = File.from('h');

        assertThat(file.move(1)).isNull();
    }

    @Test
    @DisplayName("File을 1 감소 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void minusOutOfBounds() {
        File file = File.from('a');

        assertThat(file.move(-1)).isNull();
    }
}

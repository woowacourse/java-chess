package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileTest {

    @DisplayName("File변화량으로 File을 이동한다")
    @Test
    void move() {
        File file = File.B;

        assertThat(file.move(2)).isSameAs(File.D);
        assertThat(file.move(-1)).isSameAs(File.A);
    }

    @DisplayName("이름으로 File을 찾을 수 있다")
    @Test
    void from() {
        File file = File.from("A");

        assertThat(file).isSameAs(File.A);
    }

    @DisplayName("잘못된 이름으로 File을 찾으면 예외가 발생한다")
    @Test
    void from_throws() {
        assertThatThrownBy(() -> File.from("I"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }
}

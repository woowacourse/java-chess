package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.move.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FileTest {

    @DisplayName("왼쪽으로 한 칸 이동할 수 있다")
    @Test
    void moveLeft() {
        File file = File.B;

        assertThat(file.move(Direction.LEFT)).isEqualTo(File.A);
    }

    @DisplayName("오른쪽으로 한 칸 이동할 수 있다")
    @Test
    void moveRight() {
        File file = File.B;

        assertThat(file.move(Direction.RIGHT)).isEqualTo(File.C);
    }

    @DisplayName("위아래로 움직이면 이동하지 않는다")
    @ParameterizedTest
    @CsvSource({"UP", "DOWN"})
    void moveUpDown(Direction direction) {
        File file = File.B;

        assertThat(file.move(direction)).isEqualTo(File.B);
    }

    @DisplayName("가장 왼쪽에서 왼쪽으로 더 이동할 수 없다")
    @Test
    void moveLeft_throws() {
        File file = File.A;

        assertThatThrownBy(() -> file.move(Direction.LEFT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }

    @DisplayName("가장 오른쪽에서 오른쪽으로 더 이동할 수 없다")
    @Test
    void moveRight_throws() {
        File file = File.H;

        assertThatThrownBy(() -> file.move(Direction.RIGHT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
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

package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.exception.IllegalMoveException;
import chess.domain.move.Direction;

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
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("체스판을 벗어났습니다");
    }

    @DisplayName("가장 오른쪽에서 오른쪽으로 더 이동할 수 없다")
    @Test
    void moveRight_throws() {
        File file = File.H;

        assertThatThrownBy(() -> file.move(Direction.RIGHT))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("체스판을 벗어났습니다");
    }
}

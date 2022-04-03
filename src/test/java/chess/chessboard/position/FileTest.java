package chess.chessboard.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.chessboard.position.File.A;
import static chess.chessboard.position.File.B;
import static org.assertj.core.api.Assertions.assertThat;

class FileTest {

    @DisplayName("File을 이동한다.")
    @Test
    void add() {
        assertThat(A.add(1)).isEqualTo(B);
    }

    @DisplayName("File를 이동할 수 없는 경우 false를 반환한다.")
    @Test
    void canAdd_false() {
        assertThat(A.canAdd(-1)).isFalse();
    }
}
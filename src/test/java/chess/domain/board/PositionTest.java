package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("칸의 위치 사용할 수 있다.")
    void createPosition() {
        Position position = new Position(File.A, Rank.ONE);

        assertThat(position).isEqualTo(new Position(File.A, Rank.ONE));
    }
}

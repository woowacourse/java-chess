package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    @DisplayName("위치값을 받아 포지션을 생성한다.")
    void create() {
        assertThat(Position.create("a1")).isEqualTo(new Position(File.A, Rank.ONE));
    }
}

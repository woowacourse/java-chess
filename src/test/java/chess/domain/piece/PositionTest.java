package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
    @DisplayName("Position 객체 생성 확인")
    @Test
    void 위치_객체_생성() {
        Position position = Position.of('a', '1');

        assertThat(position.getX()).isEqualTo('a');
        assertThat(position.getY()).isEqualTo('1');
    }
}

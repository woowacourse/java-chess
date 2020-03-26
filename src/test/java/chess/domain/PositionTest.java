package chess.domain;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @Test
    @DisplayName("Position 생성")
    void create() {
        assertThat(new Position(3, 'a')).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("Position 생성 - 올바르지 않은 좌표")
    void create_InvalidInput_ThrowException() {
        assertThatThrownBy(() -> new Position(3, 'z')).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Position(13, 'a')).isInstanceOf(IllegalArgumentException.class);
    }
}

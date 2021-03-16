package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("Position 객체 생성")
    @Test
    void create(){
        Position position = new Position(Vertical.A, Horizontal.FIVE);
        assertThat(position).isInstanceOf(Position.class);
    }
}
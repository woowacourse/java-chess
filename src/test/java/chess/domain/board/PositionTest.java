package chess.domain.board;

import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    @DisplayName("Position 객체 생성")
    @Test
    void create() {
        final Position position = Position.of(Vertical.A, Horizontal.FIVE);
        assertThat(position).isInstanceOf(Position.class);
    }
}
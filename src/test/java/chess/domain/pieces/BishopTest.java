package chess.domain.pieces;

import chess.domain.Position;
import chess.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    private Bishop bishop;

    @BeforeEach
    public void setUp() {
        bishop = new Bishop(new Position(2, 3), Team.WHITE);
    }

    @Test
    public void 움직여야_할_때_움직이는지_확인() {
        assertThat(bishop.canMove(new Position(3, 4))).isTrue();
        assertThat(bishop.canMove(new Position(3, 2))).isTrue();
        assertThat(bishop.canMove(new Position(1, 2))).isTrue();
    }

    @Test
    public void 움직이지_말아야_할_때_안_움직이는지_확인() {
        assertThat(bishop.canMove(new Position(3, 3))).isFalse();
        assertThat(bishop.canMove(new Position(2, 2))).isFalse();
        assertThat(bishop.canMove(new Position(2, 4))).isFalse();
    }
}
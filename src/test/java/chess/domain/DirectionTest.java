package chess.domain;

import chess.domain.board.Tile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DirectionTest {
    @Test
    void 방향확인() {
        assertThat(Direction.WEST.isSameDirection(-2, 0)).isTrue();
        assertThat(Direction.WEST.isSameDirection(2, 0)).isFalse();
        assertThat(Direction.WEST.isSameDirection(2, 2)).isFalse();
        assertThat(Direction.WEST.isSameDirection(2, -1)).isFalse();
        assertThat(Direction.EES.isSameDirection(2, -1)).isTrue();
    }

    @Test
    void nextTile_오류_범위_벗어남() {
        assertThrows(RuntimeException.class, () ->
                Direction.WEST.nextTile(Tile.of("a1")));
    }

    @Test
    void nextTile_정상() {
        assertThat(Direction.EAST.nextTile(Tile.of("a1"))).isEqualTo(Tile.of("b1"));
    }
}
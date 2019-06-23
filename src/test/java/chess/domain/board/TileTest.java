package chess.domain.board;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TileTest {
    @Test
    void 형식에_벗어나는_칸_표현1() {
        assertThrows(InvalidColumnException.class, () ->
                Tile.of("1a")
        );
    }

    @Test
    void 형식에_벗어나는_칸_표현2() {
        assertThrows(InvalidRowException.class, () ->
                Tile.of("ab")
        );
    }

    @Test
    void 형식에_벗어나는_칸_표현3() {
        assertThrows(InvalidTileException.class, () ->
                Tile.of("a12")
        );
    }

    @Test
    void null_확인() {
        assertThrows(InvalidTileException.class, () ->
                Tile.of(null)
        );
    }

    @Test
    void Tile_객체_캐싱_확인() {
        assertThat(Tile.of("a1") == Tile.of(Column.of('a'), Row.of(1)));
    }
}
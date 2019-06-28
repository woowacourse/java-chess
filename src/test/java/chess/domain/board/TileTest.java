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

    @Test
    void 가로_길이_차이_확인() {
        Tile tile1 = Tile.of("a1");
        Tile tile2 = Tile.of("b2");

        assertThat(tile1.getHeightDiff(tile2)).isEqualTo(-1);
        assertThat(tile1.getWidthDiff(tile2)).isEqualTo(-1);
    }

    @Test
    void equalRow_test1() {
        assertThat(Tile.of("a1").isEqualRow(Row.of(1))).isTrue();
    }

    @Test
    void equalRow_test2() {
        assertThat(Tile.of("a1").isEqualRow(Row.of(3))).isFalse();
    }

    @Test
    void next_test1() {
        assertThat(Tile.of("d4").next(2, 2)).isEqualTo(Tile.of("f6"));
    }

    @Test
    void next_test2() {
        assertThat(Tile.of("d4").next(-2, -1)).isEqualTo(Tile.of("b3"));
    }
}
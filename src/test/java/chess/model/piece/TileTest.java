package chess.model.piece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TileTest {
    @Test
    void 생성자_오류확인_null이_입력된_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Tile(null, new Pawn()));
    }

    @Test
    void 생성자_오류확인_좌표길이가_올바르지_않을_경우() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Tile("123", new Pawn()));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Tile("1", new Pawn()));
    }

    @Test
    void 생성자_오류확인_행이_범위를_벗어날_경우() {
        assertThat(new Tile("12", new Pawn())).isEqualTo(new Tile("12", new Pawn()));
        assertThat(new Tile("12", new Pawn())).isNotNull();
    }

    @Test
    void 기물이_타일안에_있는지_확인() {
        Tile tile = new Tile("12", new Pawn());
        assertThat(tile.isPiecePresent()).isTrue();
    }

    @Test
    void 기물이_타일에_없을_경우() {
        Tile tile = new Tile("12", new EmptyPiece());
        assertThat(tile.isPiecePresent()).isFalse();
    }
}

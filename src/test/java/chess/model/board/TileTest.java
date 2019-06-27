package chess.model.board;

import chess.model.board.vector.Vector;
import chess.model.piece.Pawn;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TileTest {
    @Test
    void 생성자_오류확인_null이_입력된_경우() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> new Tile(null, new Pawn(true, "white")));
    }

    @Test
    void 생성자_오류확인_좌표길이가_올바르지_않을_경우() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Tile("123", new Pawn(true, "white")));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Tile("1", new Pawn(true, "white")));
    }

    @Test
    void 생성자_확인_제대로_생성하는지() {
        assertThat(new Tile("12", new Pawn(true, "white")))
                .isEqualTo(new Tile("12", new Pawn(true, "white")));
        assertThat(new Tile("12", new Pawn(true, "white"))).isNotNull();
    }

    @Test
    void 기물이_타일안에_있는지_확인() {
        Tile tile = new Tile("12", new Pawn(true, "white"));
        assertThat(tile.isPiecePresent()).isTrue();
    }

    @Test
    void 기물이_타일에_없을_경우() {
        Tile tile = new Tile("12", null);
        assertThat(tile.isPiecePresent()).isFalse();
    }

    @Test
    void 경로확인() {
        Tile tile = new Tile("55", new Pawn(true, "white"));
        Coordinate sourceCoordinateX = Coordinate.valueOf(5);
        Coordinate sourceCoordinateY = Coordinate.valueOf(5);
        Coordinate targetCoordinateX = Coordinate.valueOf(5);
        Coordinate targetCoordinateY = Coordinate.valueOf(6);

        Vector vector = new Vector(Arrays.asList(sourceCoordinateX, sourceCoordinateY, targetCoordinateX, targetCoordinateY));
        assertThat(tile.findRouteFromPiece(vector)).isEqualTo(new Route(Arrays.asList("56")));
    }
}

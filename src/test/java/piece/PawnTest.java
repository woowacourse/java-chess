package piece;

import coordinate.Coordinate;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import position.Column;
import position.Row;

class PawnTest {

    @DisplayName("좌표값에서 갈 수 있는 경로를 가져온다.")
    @Test
    void getPath() {
        Coordinate coordinate = new Coordinate(new Row(0), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(1));

        Pawn pawn = new Pawn(true);

        Assertions.assertThat(pawn.getPath(coordinate, nextCoordinate)).isEqualTo(List.of(-1, 0));
    }

    @DisplayName("폰이 첫 움직임이 아닐경우 2칸을 이동할 수 없다.")
    @Test
    void cantMoveTwoWhenNotInitial() {
        Coordinate coordinate = new Coordinate(new Row(2), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(4), new Column(1));

        Pawn pawn = new Pawn(true);

        Assertions.assertThatThrownBy(() -> pawn.getPath(coordinate, nextCoordinate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 처음에만 2칸을 이동할 수 있습니다.");
    }
}

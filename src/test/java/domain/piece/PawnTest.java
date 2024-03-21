package domain.piece;

import domain.coordinate.Coordinate;
import domain.position.Column;
import domain.position.Row;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("폰의 움직임 방향을 가져온다")
    @Test
    void getPawnDirection() {
        Coordinate coordinate = new Coordinate(new Row(0), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(1));

        Pawn pawn = new Pawn(Color.BLACK);

        Assertions.assertThat(pawn.getDirection(coordinate, nextCoordinate, false)).isEqualTo(List.of(1, 0));
    }

    @DisplayName("폰이 첫 움직임이 아닐경우 2칸을 이동할 수 없다.")
    @Test
    void cantMoveTwoWhenNotInitial() {
        Coordinate coordinate = new Coordinate(new Row(2), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(4), new Column(1));

        Pawn pawn = new Pawn(Color.BLACK);

        Assertions.assertThatThrownBy(() -> pawn.getDirection(coordinate, nextCoordinate, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 처음에만 2칸을 이동할 수 있습니다.");
    }

    @DisplayName("폰이 공격이 가능한 상태라면 대각선으로도 갈 수 있다.")
    @Test
    void canMoveDiagonalWhenCanAttack() {
        Coordinate coordinate = new Coordinate(new Row(2), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(2));

        Pawn pawn = new Pawn(Color.WHITE);

        Assertions.assertThatCode(() -> pawn.getDirection(coordinate, nextCoordinate, true))
                .doesNotThrowAnyException();
    }

    @DisplayName("폰이 공격이 가능한 상태가 아니라면 대각선으로 갈 수 없다.")
    @Test
    void canNotMoveDiagonalWhenNotAttack() {
        Coordinate coordinate = new Coordinate(new Row(2), new Column(1));
        Coordinate nextCoordinate = new Coordinate(new Row(1), new Column(2));

        Pawn pawn = new Pawn(Color.WHITE);

        Assertions.assertThatThrownBy(() -> pawn.getDirection(coordinate, nextCoordinate, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}

package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("폰의 움직임 방향을 가져온다")
    @Test
    void getPawnDirection() {
        Coordinate coordinate = Coordinate.from("A7");
        Coordinate nextCoordinate = Coordinate.from("A6");

        Pawn pawn = new Pawn(Color.BLACK);

        Assertions.assertThat(pawn.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.from(List.of(-1, 0)));
    }
    
    @DisplayName("폰이 공격이 가능한 상태라면 대각선으로도 갈 수 있다.")
    @Test
    void canMoveDiagonalWhenCanAttack() {
        Coordinate coordinate = Coordinate.from("a2");
        Coordinate nextCoordinate = Coordinate.from("b3");

        Pawn pawn = new Pawn(Color.WHITE);

        Assertions.assertThatCode(() -> pawn.getDirection(coordinate, nextCoordinate, true))
                .doesNotThrowAnyException();
    }

    @DisplayName("폰이 공격이 가능한 상태가 아니라면 대각선으로 갈 수 없다.")
    @Test
    void canNotMoveDiagonalWhenNotAttack() {
        Coordinate coordinate = Coordinate.from("a2");
        Coordinate nextCoordinate = Coordinate.from("b1");

        Pawn pawn = new Pawn(Color.WHITE);

        Assertions.assertThatThrownBy(() -> pawn.getDirection(coordinate, nextCoordinate, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }
}

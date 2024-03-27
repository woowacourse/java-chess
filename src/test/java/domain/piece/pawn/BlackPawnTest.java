package domain.piece.pawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackPawnTest {

    @DisplayName("블랙 폰의 방향을 가져온다.")
    @Test
    void getPawnDirection() {
        Coordinate coordinate = Coordinate.from("A7");
        Coordinate nextCoordinate = Coordinate.from("A5");

        BlackPawn blackPawn = new BlackPawn();

        assertThat(blackPawn.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.DOWN);
    }

    @DisplayName("블랙 폰에게 해당하는 방향이 없는 경우 에러를 발생한다.")
    @Test
    void pawnDirectionNotFound() {
        Coordinate coordinate = Coordinate.from("A2");
        Coordinate nextCoordinate = Coordinate.from("A3");

        BlackPawn blackPawn = new BlackPawn();

        assertThatThrownBy(() -> blackPawn.getDirection(coordinate, nextCoordinate, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하는 움직임 전략이 없습니다.");
    }

    @DisplayName("start 위치가 폰의 시작 위치와 일치하는 경우 true를 반환한다.")
    @Test
    void isFirstPosition() {
        Coordinate coordinate = Coordinate.from("A7");

        BlackPawn blackPawn = new BlackPawn();

        assertTrue(blackPawn.isFirstPosition(coordinate));
    }

    @DisplayName("start 위치가 폰의 시작 위치와 일치하지 않는 경우 false 를 반환한다.")
    @Test
    void isNotFirstPosition() {
        Coordinate coordinate = Coordinate.from("A5");

        BlackPawn blackPawn = new BlackPawn();

        assertFalse(blackPawn.isFirstPosition(coordinate));
    }
}

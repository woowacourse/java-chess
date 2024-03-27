package domain.piece.pawn;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhitePawnTest {


    @DisplayName("흰색 폰의 방향을 가져온다.")
    @Test
    void getPawnDirection() {
        Coordinate coordinate = Coordinate.from("A2");
        Coordinate nextCoordinate = Coordinate.from("A4");

        WhitePawn whitePawn = new WhitePawn();

        Assertions.assertThat(whitePawn.getDirection(coordinate, nextCoordinate, false))
                .isEqualTo(Direction.UP);
    }

    @DisplayName("흰색 폰에게 해당하는 방향이 없는 경우 에러를 발생한다.")
    @Test
    void pawnDirectionNotFound() {
        Coordinate coordinate = Coordinate.from("A2");
        Coordinate nextCoordinate = Coordinate.from("F4");

        WhitePawn whitePawn = new WhitePawn();

        Assertions.assertThatThrownBy(() -> whitePawn.getDirection(coordinate, nextCoordinate, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰에게 가능한 방향이 없습니다.");
    }

    @DisplayName("start 위치가 폰의 시작 위치와 일치하는 경우 true를 반환한다.")
    @Test
    void isFirstPosition() {
        Coordinate coordinate = Coordinate.from("a2");

        WhitePawn whitePawn = new WhitePawn();

        assertTrue(whitePawn.isFirstPosition(coordinate));
    }

    @DisplayName("start 위치가 폰의 시작 위치와 일치하지 않는 경우 false 를 반환한다.")
    @Test
    void isNotFirstPosition() {
        Coordinate coordinate = Coordinate.from("a7");

        WhitePawn whitePawn = new WhitePawn();

        assertFalse(whitePawn.isFirstPosition(coordinate));
    }
}

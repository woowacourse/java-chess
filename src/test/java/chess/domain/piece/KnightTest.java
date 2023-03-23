package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KnightTest {

    @Test
    void isMovable_메서드로_checkMovableToDestination_에러발생_테스트() {
        //given, when
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "4");
        Color destinationColor = Color.WHITE;

        //then
        assertThatThrownBy(() -> knight.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_checkDirection_에러발생_테스트() {
        //given, when
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "5");
        Color destinationColor = Color.BLACK;

        //then
        assertThatThrownBy(() -> knight.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_checkDistance_에러발생_테스트() {
        //given, when
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("b", "4");
        Color destinationColor = Color.BLACK;

        //then
        assertThatThrownBy(() -> knight.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_올바른_반환값_테스트() {
        //given, when
        Knight knight = new Knight(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "4");
        Color destinationColor = Color.BLACK;

        //then
        assertDoesNotThrow(() -> knight.isMovable(start, end, destinationColor));
    }
}

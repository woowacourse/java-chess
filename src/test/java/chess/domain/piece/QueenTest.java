package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QueenTest {

    @Test
    void isMovable_메서드로_checkMovableToDestination_에러발생_테스트() {
        //given, when
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "3");
        Color destinationColor = Color.WHITE;

        //then
        assertThatThrownBy(() -> queen.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_메서드로_checkDirection_에러발생_테스트() {
        //given, when
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "7");
        Color destinationColor = Color.BLACK;

        //then
        assertThatThrownBy(() -> queen.isMovable(start, end, destinationColor))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isMovable_올바른_반환값_테스트() {
        //given, when
        Queen queen = new Queen(Color.WHITE);
        Position start = new Position("b", "2");
        Position end = new Position("a", "3");
        Color destinationColor = Color.BLACK;

        //then
        assertDoesNotThrow(() -> queen.isMovable(start, end, destinationColor));
    }
}

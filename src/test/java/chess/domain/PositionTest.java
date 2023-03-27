package chess.domain;

import chess.direction.Direction;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PositionTest {

    @Test
    void findGapOfRank_메서드_테스트() {
        //given
        Position start = new Position("a", "3");
        Position destination = new Position("b", "5");
        //when
        int expected = 2;
        int actual = start.findGapOfRank(destination);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findGapOfColum_메서드_테스트() {
        //given
        Position start = new Position("a", "3");
        Position destination = new Position("b", "5");
        //when
        int expected = 1;
        int actual = start.findGapOfColum(destination);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void moveDirection_메서드_테스트() {
        //given
        Position start = new Position("a", "3");
        Direction direction = Direction.BOTTOM;
        //when
        Position expected = new Position("a", "2");
        Position actual = start.moveDirection(direction);

        //then
        assertEquals(expected, actual);
    }
}

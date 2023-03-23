package chess.domain;

import chess.direction.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {

    @Test
    void findGapOfRank() {
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
    void findGapOfColum() {
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
    void moveDirection() {
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

package model.direction;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("해당하는 반대 방향의 Direction을 반환한다.")
    void toOppositeDirection_ShouldReturnOppositeDirection_WhenGivenDireciton() {

        assertAll(() -> {
            assertEquals(Direction.S, Direction.N.toOppositeDirection());
            assertEquals(Direction.N, Direction.S.toOppositeDirection());
            assertEquals(Direction.W, Direction.E.toOppositeDirection());
            assertEquals(Direction.E, Direction.W.toOppositeDirection());
            assertEquals(Direction.S, Direction.N.toOppositeDirection());
            assertEquals(Direction.SW, Direction.NE.toOppositeDirection());
            assertEquals(Direction.SE, Direction.NW.toOppositeDirection());
            assertEquals(Direction.NW, Direction.SE.toOppositeDirection());
            assertEquals(Direction.NE, Direction.SW.toOppositeDirection());
            assertEquals(Direction.SSW, Direction.NNE.toOppositeDirection());
            assertEquals(Direction.SSE, Direction.NNW.toOppositeDirection());
            assertEquals(Direction.NNW, Direction.SSE.toOppositeDirection());
            assertEquals(Direction.NNE, Direction.SSW.toOppositeDirection());
            assertEquals(Direction.WWS, Direction.EEN.toOppositeDirection());
            assertEquals(Direction.WWN, Direction.EES.toOppositeDirection());
            assertEquals(Direction.EES, Direction.WWN.toOppositeDirection());
            assertEquals(Direction.EEN, Direction.WWS.toOppositeDirection());
        });
    }
}

package chess.domain.domain;

import chess.domain.MoveRule;
import chess.domain.Navigator;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.HorizonDirection;
import chess.domain.direction.VerticalDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveRuleTest {
    @Test
    void 불가능한_방향인_경우_예외_반환() {
        List<Direction> possibleDirection = Arrays.asList(HorizonDirection.getInstance(), VerticalDirection.getInstance());
        MoveRule moveRule = new MoveRule(possibleDirection, 2);

        Navigator navigator = new Navigator(Position.valueOf("a1"), Position.valueOf("b2"));
        assertThrows(IllegalArgumentException.class, () -> moveRule.judge(navigator));
    }

    @Test
    void 불가능한_이동_횟수인_경우_예외_반환() {
        List<Direction> possibleDirection = Arrays.asList(HorizonDirection.getInstance(), VerticalDirection.getInstance());
        MoveRule moveRule = new MoveRule(possibleDirection, 2);

        Navigator navigator = new Navigator(Position.valueOf("a1"), Position.valueOf("a4"));
        assertThrows(IllegalArgumentException.class, () -> moveRule.judge(navigator));
    }
}

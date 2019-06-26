package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    private Piece testBishop;

    @BeforeEach
    void setUp() {
        testBishop = new Bishop(Player.BLACK, Position.of("f3"));
    }

    @Test
    void findPossiblePositionsTest() {
        List<Position> actual = new ArrayList<>();
        testBishop.getIteratorsOfPossibleDestinations().forEach(i -> {
            while (i.hasNext()) {
                actual.add(i.next());
            }
        });
        List<Position> expected = new ArrayList<>();
        Direction.diagonal().forEach(dir -> {
            Position pos = testBishop.position();
            while (pos.testForward(dir)) {
                pos = pos.moveForward(dir);
                expected.add(pos);
            }
        });
        Collections.sort(actual);
        Collections.sort(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
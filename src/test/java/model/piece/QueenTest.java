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

class QueenTest {
    private Piece testQueen;

    @BeforeEach
    void setUp() {
        testQueen = new Queen(Player.WHITE, Position.of("e3"));
    }

    @Test
    void findPossiblePositionsTest() {
        List<Position> actual = new ArrayList<>();
        testQueen.findPossiblePositions().forEach(i -> {
                                                    while (i.hasNext()) {
                                                        actual.add(i.next());
                                                    }
                                                });
        List<Position> expected = new ArrayList<>();
        Direction.rotateClockwiseFromTo(Direction.NORTH, 8).forEach(dir -> {
                                                                        Position pos = testQueen.position();
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
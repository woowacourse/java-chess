package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class KingTest {
    Piece testKing;

    @BeforeEach
    void setUp() {
        testKing = new King(Player.WHITE, Position.of("e3"));
    }

    @Test
    void isAKingTest() {
        assertThat(testKing.isKing()).isTrue();
    }

    @Test
    void findPossiblePositionsTest() {
        List<Position> actual = testKing.findPossiblePositions()
                                        .map(Iterator::next)
                                        .collect(Collectors.toList());
        List<Position> expected = Direction.rotateClockwiseFromTo(Direction.NORTH, 8)
                                            .map(Position.of("e3")::moveForward)
                                            .collect(Collectors.toList());
        Collections.sort(actual);
        Collections.sort(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
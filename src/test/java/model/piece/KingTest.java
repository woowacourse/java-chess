package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {
    private Piece testKing;

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
        assertThat(
                testKing.getIteratorsOfPossibleDestinations()
                        .map(Iterator::next)
                        .sorted()
                        .collect(Collectors.toList())
        ).isEqualTo(
                Direction.every()
                        .map(Position.of("e3")::moveForward)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}
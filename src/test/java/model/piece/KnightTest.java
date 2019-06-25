package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class KnightTest {
    Piece testKnight;

    @BeforeEach
    void setUp() {
        testKnight = new Knight(Player.BLACK, Position.of("d6"));
    }

    @Test
    void findPossiblePositionsTest() {
        List<Position> actual = testKnight.findPossiblePositions()
                                        .map(Iterator::next)
                                        .collect(Collectors.toList());
        List<Position> expected = Arrays.asList(
                Position.of("c8"),
                Position.of("e8"),
                Position.of("f7"),
                Position.of("f5"),
                Position.of("e4"),
                Position.of("c4"),
                Position.of("b5"),
                Position.of("b7")
        );
        Collections.sort(actual);
        Collections.sort(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
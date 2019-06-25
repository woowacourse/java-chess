package model.piece;

import model.board.Position;
import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private Piece testKnight;

    @BeforeEach
    void setUp() {
        testKnight = new Knight(Player.BLACK, Position.of("d6"));
    }

    @Test
    void findPossiblePositionsTest() {
        List<Position> actual = testKnight.findPossiblePositions()
                                        .map(Iterator::next)
                                        .collect(Collectors.toList());
        List<Position> expected = Stream.of("c8", "e8", "f7", "f5", "e4", "c4", "b5", "b7")
                                        .map(Position::of)
                                        .collect(Collectors.toList());
        Collections.sort(actual);
        Collections.sort(expected);
        assertThat(actual).isEqualTo(expected);
    }
}
package model.piece;

import model.board.Position;
import model.game.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private Piece testKnight;

    @BeforeEach
    void setUp() {
        testKnight = new Knight(Color.BLACK, Position.of("d6"));
    }

    @Test
    void findPossiblePositionsTest() {
        assertThat(
                testKnight.getIteratorsOfPossibleDestinations()
                            .map(Iterator::next)
                            .sorted()
                            .collect(Collectors.toList())
        ).isEqualTo(
                Stream.of("c8", "e8", "f7", "f5", "e4", "c4", "b5", "b7")
                        .map(Position::of)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}
package model.board;

import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private Board testBoard;
    private Hand testHand;

    @BeforeEach
    void setUp() {
        testBoard = new Board();
        testHand = new Hand(Player.WHITE, testBoard);
    }

    @Test
    void getPossibleDestinationsPawn() {
        assertThat(
                testHand.getPossibleDestinations(Position.of("b2"))
                        .stream()
                        .sorted()
        ).isEqualTo(Arrays.asList(Position.of("b3"), Position.of("b4")));
    }

    @Test
    void getPossibleDestinationsRookInTheMiddle() {
        testBoard.movePieceFromTo(Position.of("a1"), Position.of("e4"));
        testBoard.movePieceFromTo(Position.of("b1"), Position.of("e6"));
        testBoard.movePieceFromTo(Position.of("c1"), Position.of("b4"));
        assertThat(
                testHand.getPossibleDestinations(Position.of("e4"))
                        .stream()
                        .sorted()
                        .collect(Collectors.toList())
        ).isEqualTo(
                Stream.of("e3", "e5", "c4", "d4", "f4", "g4", "h4")
                        .map(Position::of)
                        .sorted()
                        .collect(Collectors.toList())
        );
    }
}
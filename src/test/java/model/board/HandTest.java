package model.board;

import model.game.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private Board testBoard;
    private Hand testHand;

    @BeforeEach
    void setUp() {
        testBoard = new Board();
        testHand = new Hand(Color.WHITE, testBoard);
    }

    @Test
    void getPossibleDestinationsOfRookInTheMiddle() {
        testBoard.movePieceFromTo(Position.of("a1"), Position.of("e4"));
        testBoard.movePieceFromTo(Position.of("b1"), Position.of("e6"));
        testBoard.movePieceFromTo(Position.of("c1"), Position.of("b4"));
        assertThat(
                testHand.getPossibleDestinations(Position.of("e4"))
                        .stream()
                        .sorted()
        ).isEqualTo(
                Stream.of("e3", "c4", "d4", "f4", "g4", "h4", "e5")
                        .map(Position::of)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void moveEnemysPieceTest() {
        assertThat(
                testHand.tryToMoveFromTo(Position.of("d7"), Position.of("d5"))
        ).isFalse();
    }

    @Test
    void illegalMovementTest() {
        assertThat(
                testHand.tryToMoveFromTo(Position.of("a1"), Position.of("a2"))
        ).isFalse();
    }

    @Test
    void getPossibleDestinationsOfPawnWhenHasNotMovedButBlockedInTheWayByOwnPiece() {
        testBoard.movePieceFromTo(Position.of("a1"), Position.of("b3"));
        assertThat(
                testHand.getPossibleDestinations(Position.of("b2"))
        ).isEqualTo(new ArrayList());
    }
}
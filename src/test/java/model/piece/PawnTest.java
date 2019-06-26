package model.piece;

import model.board.Position;
import model.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    private Pawn testPawn;

    @BeforeEach
    void setUp() {
        testPawn = new Pawn(Player.WHITE, Position.of("c4"));
    }

    @Test
    void isPawnTest() {
        assertThat(testPawn.isPawn()).isTrue();
    }

    @Test
    void possibleForwardPositionsTest() {
        assertThat(
                testPawn.possibleForwardDestinations().collect(Collectors.toList())
        ).contains(Position.of("c5"), Position.of("c6"));
    }

    @Test
    void possibleForwardPositionAfterMoved() {
        testPawn.move(Position.of("a2"));
        assertThat(
                testPawn.possibleForwardDestinations().collect(Collectors.toList())
        ).contains(Position.of("a3"));
    }

    @Test
    void possibleDiagonalPositionsTest() {
        assertThat(
                testPawn.possibleDiagonalDestinations().collect(Collectors.toList())
        ).contains(Position.of("b5"), Position.of("d5"));
    }

    @Test
    void possibleDiagonalPositionsAtTheEdge() {
        testPawn.move(Position.of("h7"));
        assertThat(
                testPawn.possibleDiagonalDestinations().collect(Collectors.toList())
        ).contains(Position.of("g8"));
    }
}
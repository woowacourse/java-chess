package chess.domain.piece;

import chess.domain.side.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Direction.*;
import static chess.domain.side.Color.BLACK;
import static chess.domain.side.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    static Stream<Arguments> canAttackDummy() {
        return Stream.of(
                Arguments.of(SOUTH_WEST, 1, BLACK, WHITE),
                Arguments.of(SOUTH_EAST, 1, BLACK, WHITE),
                Arguments.of(NORTH_WEST, 1, WHITE, BLACK),
                Arguments.of(NORTH_EAST, 1, WHITE, BLACK)
        );
    }

    static Stream<Arguments> canNotAttackDummy() {
        return Stream.of(
                Arguments.of(SOUTH_WEST, 1, BLACK, BLACK),
                Arguments.of(SOUTH_EAST, 1, BLACK, BLACK),
                Arguments.of(NORTH_WEST, 1, WHITE, WHITE),
                Arguments.of(NORTH_EAST, 1, WHITE, WHITE),
                Arguments.of(SOUTH_WEST, 1, WHITE, BLACK),
                Arguments.of(SOUTH_EAST, 1, WHITE, BLACK),
                Arguments.of(NORTH_WEST, 1, BLACK, WHITE),
                Arguments.of(NORTH_EAST, 1, BLACK, WHITE)
        );
    }

    @Test
    @DisplayName("이동할 수 있는지 확인한다.")
    void isMovable() {
        // when
        Pawn blackPawn = new Pawn(Color.BLACK, Role.PAWN);
        Pawn whitePawn = new Pawn(Color.WHITE, Role.PAWN);

        // expected
        assertThat(blackPawn.canMove(SOUTH, 1)).isTrue();
        assertThat(whitePawn.canMove(NORTH, 1)).isTrue();
    }

    @Test
    @DisplayName("이동할 수 없는지 확인한다.")
    void canNotMove() {
        // when
        Pawn blackPawn = new Pawn(Color.BLACK, Role.PAWN);
        Pawn whitePawn = new Pawn(Color.WHITE, Role.PAWN);

        // expected
        assertThat(blackPawn.canMove(NORTH, 1)).isFalse();
        assertThat(whitePawn.canMove(SOUTH, 1)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("canAttackDummy")
    @DisplayName("공격할 수 있는지 확인한다.")
    void canAttack(final Direction direction, final int distance, final Color color, final Color opponentSide) {
        // when
        Pawn pawn = new Pawn(color, Role.PAWN);
        Pawn opponentPiece = new Pawn(opponentSide, Role.PAWN);

        // expected
        assertThat(pawn.canAttack(direction, distance, opponentPiece)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("canNotAttackDummy")
    @DisplayName("공격할 수 없는지 확인한다.")
    void canNotAttack(final Direction direction, final int distance, final Color color, final Color opponentSide) {
        // when
        Pawn pawn = new Pawn(color, Role.PAWN);
        Pawn opponentPiece = new Pawn(opponentSide, Role.PAWN);
        // expected
        assertThat(pawn.canAttack(direction, distance, opponentPiece)).isFalse();
    }
}

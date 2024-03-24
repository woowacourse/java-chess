package chess.model.piece;

import static chess.model.Fixtures.A2;
import static chess.model.Fixtures.A3;
import static chess.model.Fixtures.A6;
import static chess.model.Fixtures.A7;
import static chess.model.Fixtures.A8;
import static chess.model.Fixtures.B3;
import static chess.model.Fixtures.B5;
import static chess.model.Fixtures.B7;
import static chess.model.Fixtures.C6;
import static chess.model.Fixtures.G1;
import static chess.model.Fixtures.G2;
import static chess.model.Fixtures.G4;
import static chess.model.Fixtures.H1;
import static chess.model.Fixtures.H2;
import static chess.model.Fixtures.H7;
import static chess.model.Fixtures.H8;
import static chess.model.material.Color.BLACK;
import static chess.model.material.Color.WHITE;
import static chess.model.material.Type.PAWN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @DisplayName("Black Pawn이 전진 1칸 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideBlackPawnSourceAndTargetWithExpected")
    void blackPawnCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Pawn(PAWN, BLACK);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideBlackPawnSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(A7, A6, true),
            Arguments.of(H7, H8, false)
        );
    }

    @DisplayName("White Pawn이 전진 1칸 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideWhitePawnSourceAndTargetWithExpected")
    void whitePawnCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Pawn(PAWN, WHITE);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideWhitePawnSourceAndTargetWithExpected() {
        return Stream.of(
            Arguments.of(A2, A3, true),
            Arguments.of(G1, H1, false)
        );
    }

    @DisplayName("White Pawn이 최초 2칸 전진 이동이면 canMove true를 반환한다")
    @Test
    void whitePawnCanInitialMove() {
        Piece piece = new Pawn(PAWN, WHITE);
        boolean canMove = piece.canMove(G2, G4);
        assertThat(canMove).isTrue();
    }

    @DisplayName("Black Pawn이 최초 2칸 전진 이동이면 움직일 수 있다")
    @Test
    void blackPawnCanInitialMove() {
        Piece piece = new Pawn(PAWN, BLACK);
        boolean canMove = piece.canMove(B7, B5);
        assertThat(canMove).isTrue();
    }

    @DisplayName("White Pawn이 전방 대각선 1칸 공격이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideWhitePawnAttackMovePosition")
    void whitePawnCanDiagonalMove(Position source, Position target, boolean expected) {
        Pawn piece = new Pawn(PAWN, WHITE);
        boolean canMove = piece.canAttack(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideWhitePawnAttackMovePosition() {
        return Stream.of(
            Arguments.of(A2, B3, true),
            Arguments.of(H2, G1, false)
        );
    }

    @DisplayName("Black Pawn이 전방 대각선 1칸 공격이면 움직일 수 있다")
    @ParameterizedTest
    @MethodSource("provideBlackPawnAttackMovePosition")
    void blackPawnCanDiagonalMove(Position source, Position target, boolean expected) {
        Pawn piece = new Pawn(PAWN, BLACK);
        boolean canMove = piece.canAttack(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideBlackPawnAttackMovePosition() {
        return Stream.of(
            Arguments.of(B7, C6, true),
            Arguments.of(B7, A8, false)
        );
    }
}

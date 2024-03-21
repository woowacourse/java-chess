package chess.model.piece;

import static chess.model.piece.PieceType.BLACK_PAWN;
import static chess.model.piece.PieceType.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Position;
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
        Piece piece = new Pawn(BLACK_PAWN);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideBlackPawnSourceAndTargetWithExpected() {
        return Stream.of(
                Arguments.of(new Position(1, 0), new Position(2, 0), true),
                Arguments.of(new Position(1, 7), new Position(0, 7), false)
        );
    }

    @DisplayName("White Pawn이 전진 1칸 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @ParameterizedTest
    @MethodSource("provideWhitePawnSourceAndTargetWithExpected")
    void whitePawnCanMove(Position source, Position target, boolean expected) {
        Piece piece = new Pawn(WHITE_PAWN);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> provideWhitePawnSourceAndTargetWithExpected() {
        return Stream.of(
                Arguments.of(new Position(6, 0), new Position(5, 0), true),
                Arguments.of(new Position(6, 7), new Position(7, 7), false)
        );
    }

    @DisplayName("White Pawn이 최초 2칸 전진 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @Test
    void whitePawnCanInitialMove() {
        Piece piece = new Pawn(WHITE_PAWN);
        Position source = new Position(6, 0);
        Position target = new Position(4, 0);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(true);
    }

    @DisplayName("Black Pawn이 최초 2칸 전진 이동이면 canMove true를 반환하고, 아니면 false를 반환한다")
    @Test
    void blackPawnCanInitialMove() {
        Piece piece = new Pawn(BLACK_PAWN);
        Position source = new Position(1, 0);
        Position target = new Position(3, 0);
        boolean canMove = piece.canMove(source, target);
        assertThat(canMove).isEqualTo(true);
    }
}

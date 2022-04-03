package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.Pawn;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    @ParameterizedTest
    @MethodSource("PawnRightMovement")
    @DisplayName("폰은 기본적으로 수직 한 칸 이동한다")
    void move_verticalOneStep(Position source, Position target) {
        Role pawn = new Pawn();
        assertThatCode(() -> pawn.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> PawnRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a2")),
                Arguments.of(Position.of("e5"), Position.of("e4"))
        );
    }

    @ParameterizedTest
    @MethodSource("PawnRightMovementOnStart")
    @DisplayName("폰은 처음 이동할 때는 수직 2칸 까지 움직일 수 있다")
    void move_verticalTwoStep_OnStart(Position source, Position target) {
        Role pawn = new Pawn();
        assertThatCode(() -> pawn.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> PawnRightMovementOnStart() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("a4")),
                Arguments.of(Position.of("h7"), Position.of("h5"))
        );
    }

    @ParameterizedTest
    @MethodSource("PawnRightMovementOnCatch")
    @DisplayName("폰은 상대방을 잡을 땐 대각 한 칸도 이동할 수 있다")
    void move_diagonalOneStep(Position source, Position target) {
        Role pawn = new Pawn();
        assertThatCode(() -> pawn.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> PawnRightMovementOnCatch() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("b2")),
                Arguments.of(Position.of("e5"), Position.of("d4"))
        );
    }

    @ParameterizedTest
    @MethodSource("PawnWrongMovement")
    @DisplayName("폰이 시작점이 아닌데 직선 2칸 이상 이동할 시, 또는 대각 2칸 이상 이동할 시 예외를 발생한다")
    void moveOverTwoBlocks_notOnStart_throwException(Position source, Position target) {
        Role pawn = new Pawn();
        assertThatThrownBy(() -> pawn.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰");
    }

    private static Stream<Arguments> PawnWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("a3"), Position.of("a5")),
                Arguments.of(Position.of("a1"), Position.of("c3"))
        );
    }
}

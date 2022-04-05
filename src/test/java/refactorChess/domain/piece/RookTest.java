package refactorChess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

class RookTest {

    @Test
    @DisplayName("룩을 생성할 수 있다.")
    void createValidPieceOfRook() {
        assertThat(new Rook(PieceColor.WHITE, Position.valueOf("a1"))).isInstanceOf(Rook.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validRookMovableTestSet")
    @DisplayName("룩이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfRook(Position from, Position to, Direction direction) {
        final Rook rook = new Rook(PieceColor.WHITE, from);

        assertThat(rook.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validRookMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3"), Direction.NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("a1"), Direction.SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("c1"), Direction.EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a1"), Direction.WEST)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("invalidRookMovableTestSet")
    @DisplayName("룩이 잘못된 방향으로 이동하는 경우 예외가 발생한다.")
    void inValidMovablePieceOfRook(Position from, Position to, Direction direction) {
        final Rook rook = new Rook(PieceColor.WHITE, from);

        assertThatThrownBy(() -> rook.findByDirection(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("직선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidRookMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), Position.valueOf("b3"), Direction.NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("b1"), Direction.SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("c3"), Direction.EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a3"), Direction.WEST)
        );
    }
}

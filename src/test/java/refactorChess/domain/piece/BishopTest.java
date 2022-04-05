package refactorChess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static refactorChess.domain.board.Direction.NORTH_EAST;
import static refactorChess.domain.board.Direction.NORTH_WEST;
import static refactorChess.domain.board.Direction.SOUTH_EAST;
import static refactorChess.domain.board.Direction.SOUTH_WEST;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

class BishopTest {

    @Test
    @DisplayName("비숍을 생성할 수 있다.")
    void createValidPieceOfBishop() {
        assertThat(new Bishop(PieceColor.WHITE, Position.valueOf("c1"))).isInstanceOf(Bishop.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validBishopMovableTestSet")
    @DisplayName("비숍이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfBishop(Position from, Position to, Direction direction) {
        final Bishop bishop = new Bishop(PieceColor.WHITE, from);

        assertThat(bishop.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validBishopMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("c1"), Position.valueOf("e3"), Direction.NORTH_EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a3"), Direction.NORTH_WEST),
                Arguments.of(Position.valueOf("e3"), Position.valueOf("c1"), Direction.SOUTH_WEST),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("c1"), Direction.SOUTH_EAST)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("invalidBishopMovableTestSet")
    @DisplayName("비숍이 잘못된 방향으로 이동하는 경우 예외가 발생한다.")
    void inValidMovablePieceOfBishop(Position from, Position to, Direction direction) {
        final Bishop bishop = new Bishop(PieceColor.WHITE, from);

        assertThatThrownBy(() -> bishop.findByDirection(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidBishopMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3"), Direction.NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("a1"), Direction.SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3"), Direction.EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a1"), Direction.WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("validFindMovePathToTargetPieceFromTheSourcePieceOfBishop")
    @DisplayName("출발점 기물인 비숍은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void validFindMovePathToTargetPieceFromTheSourcePieceOfBishop(
            Position from, Position to, Piece targetPiece, Direction direction) {

        final Bishop bishop = new Bishop(PieceColor.WHITE, from);

        assertThat(bishop.findByMovePath(targetPiece)).isEqualTo(new MovePath(from, to, direction));
    }

    static Stream<Arguments> validFindMovePathToTargetPieceFromTheSourcePieceOfBishop() {
        return Stream.of(
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("e3"), new Blank(Position.valueOf("e3")), NORTH_EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("a3"), new Blank(Position.valueOf("a3")), NORTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("a1"), new Blank(Position.valueOf("a1")), SOUTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("e1"), new Blank(Position.valueOf("e1")), SOUTH_EAST)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfBishop")
    @DisplayName("비숍이 잘못된 방향으로 이동하는 경우 예외가 발생한다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfBishop(Position from, Piece targetPiece) {

        final Bishop bishop = new Bishop(PieceColor.WHITE, from);

        assertThatThrownBy(() -> bishop.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfBishop() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("a3"), new Blank(Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("c1"))),
                Arguments.of(Position.valueOf("c1"), new Blank(Position.valueOf("a1")))
        );
    }
}

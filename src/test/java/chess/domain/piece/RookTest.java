package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static chess.domain.board.Direction.EAST;
import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.WEST;

import chess.domain.board.MovePath;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import chess.domain.board.Direction;
import chess.domain.board.Position;

class RookTest {

    @Test
    @DisplayName("룩을 생성할 수 있다.")
    void createValidPieceOfRook() {
        Assertions.assertThat(new Rook(PieceColor.WHITE, Position.valueOf("a1"))).isInstanceOf(Rook.class);
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
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3"), NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("a1"), SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("c1"), EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a1"), WEST)
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
                Arguments.of(Position.valueOf("a1"), Position.valueOf("b3"), NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("b1"), SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("c3"), EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a3"), WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("validFindMovePathToTargetPieceFromTheSourcePieceOfRook")
    @DisplayName("출발점 기물인 룩은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void validFindMovePathToTargetPieceFromTheSourcePieceOfRook(
            Position from, Position to, Piece targetPiece, Direction direction) {

        final Rook rook = new Rook(PieceColor.WHITE, from);

        assertThat(rook.findByMovePath(targetPiece)).isEqualTo(new MovePath(from, to, direction));
    }

    static Stream<Arguments> validFindMovePathToTargetPieceFromTheSourcePieceOfRook() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), Position.valueOf("a3"), new Blank(Position.valueOf("a3")), NORTH),
                Arguments.of(Position.valueOf("a3"), Position.valueOf("a1"), new Blank(Position.valueOf("a1")), SOUTH),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("c1"), new Blank(Position.valueOf("c1")), EAST),
                Arguments.of(Position.valueOf("c1"), Position.valueOf("a1"), new Blank(Position.valueOf("a1")), WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfRook")
    @DisplayName("룩이 잘못된 방향으로 이동하는 경우 예외가 발생한다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfRook(Position from, Piece targetPiece) {

        final Rook rook = new Rook(PieceColor.WHITE, from);

        assertThatThrownBy(() -> rook.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("직선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfRook() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("c3"))),
                Arguments.of(Position.valueOf("a3"), new Blank(Position.valueOf("c1"))),
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("b2"))),
                Arguments.of(Position.valueOf("c1"), new Blank(Position.valueOf("b2")))
        );
    }
}

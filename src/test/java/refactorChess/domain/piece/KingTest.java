package refactorChess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static refactorChess.domain.board.Direction.EAST;
import static refactorChess.domain.board.Direction.NORTH;
import static refactorChess.domain.board.Direction.NORTH_EAST;
import static refactorChess.domain.board.Direction.NORTH_EAST_EAST;
import static refactorChess.domain.board.Direction.NORTH_NORTH_EAST;
import static refactorChess.domain.board.Direction.NORTH_NORTH_WEST;
import static refactorChess.domain.board.Direction.NORTH_WEST;
import static refactorChess.domain.board.Direction.NORTH_WEST_WEST;
import static refactorChess.domain.board.Direction.SOUTH;
import static refactorChess.domain.board.Direction.SOUTH_EAST;
import static refactorChess.domain.board.Direction.SOUTH_EAST_EAST;
import static refactorChess.domain.board.Direction.SOUTH_SOUTH_EAST;
import static refactorChess.domain.board.Direction.SOUTH_SOUTH_WEST;
import static refactorChess.domain.board.Direction.SOUTH_WEST;
import static refactorChess.domain.board.Direction.SOUTH_WEST_WEST;
import static refactorChess.domain.board.Direction.WEST;
import static refactorChess.domain.piece.PieceColor.NONE;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

class KingTest {

    @Test
    @DisplayName("킹을 생성할 수 있다.")
    void createValidPieceOfKing() {
        assertThat(new King(PieceColor.WHITE, Position.valueOf("c1"))).isInstanceOf(King.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validKingMovableTestSet")
    @DisplayName("킹이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfKing(Position from, Position to, Direction direction) {
        final King king = new King(PieceColor.WHITE, from);

        assertThat(king.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validKingMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d1"), Position.valueOf("d2"), Direction.NORTH),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d2"), Direction.SOUTH),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("c1"), Direction.WEST),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("b1"), Direction.EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("e2"), Direction.NORTH_EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("c2"), Direction.NORTH_WEST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("e2"), Direction.SOUTH_EAST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("c2"), Direction.SOUTH_WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("validFindMovePathToTargetPieceFromTheSourcePieceOfKing")
    @DisplayName("출발점 기물인 킹은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void validFindMovePathToTargetPieceFromTheSourcePieceOfKing(
            Position from, Position to, Piece targetPiece, Direction direction) {

        final King king = new King(PieceColor.WHITE, from);

        assertThat(king.findByMovePath(targetPiece)).isEqualTo(new MovePath(from, to, direction));
    }

    static Stream<Arguments> validFindMovePathToTargetPieceFromTheSourcePieceOfKing() {
        return Stream.of(
                Arguments.of(
                        Position.valueOf("a1"), Position.valueOf("a3"),
                        new Blank(NONE, Position.valueOf("a3")), NORTH),
                Arguments.of(
                        Position.valueOf("a3"), Position.valueOf("a1"),
                        new Blank(NONE, Position.valueOf("a1")), SOUTH),
                Arguments.of(
                        Position.valueOf("a1"), Position.valueOf("c1"),
                        new Blank(NONE, Position.valueOf("c1")), EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("a1"),
                        new Blank(NONE, Position.valueOf("a1")), WEST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("e3"),
                        new Blank(NONE, Position.valueOf("e3")), NORTH_EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("a3"),
                        new Blank(NONE, Position.valueOf("a3")), NORTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("a1"),
                        new Blank(NONE, Position.valueOf("a1")), SOUTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("e1"),
                        new Blank(NONE, Position.valueOf("e1")), SOUTH_EAST)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing")
    @DisplayName("출발점 기물인 킹은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing(Position from, Piece targetPiece) {
        final King king = new King(PieceColor.WHITE, from);

        assertThatThrownBy(() -> king.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("직선 또는 대각선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing() {
        return Stream.of(
                Arguments.of(Position.valueOf("b1"), new Blank(NONE, Position.valueOf("c3"))),
                Arguments.of(Position.valueOf("b1"), new Blank(NONE, Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("c2"), new Blank(NONE, Position.valueOf("e3"))),
                Arguments.of(Position.valueOf("c2"), new Blank(NONE, Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("b3"), new Blank(NONE, Position.valueOf("c1"))),
                Arguments.of(Position.valueOf("b3"), new Blank(NONE, Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("c2"), new Blank(NONE, Position.valueOf("e1"))),
                Arguments.of(Position.valueOf("c2"), new Blank(NONE, Position.valueOf("a1")))
        );
    }
}

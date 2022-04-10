package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static chess.domain.board.Direction.EAST;
import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;
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

class KingTest {

    @Test
    @DisplayName("킹을 생성할 수 있다.")
    void createValidPieceOfKing() {
        Assertions.assertThat(new King(PieceColor.WHITE, Position.valueOf("c1"))).isInstanceOf(King.class);
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
                        Position.valueOf("a1"), Position.valueOf("a2"), new Blank(Position.valueOf("a2")), NORTH),
                Arguments.of(
                        Position.valueOf("a3"), Position.valueOf("a2"), new Blank(Position.valueOf("a2")), SOUTH),
                Arguments.of(
                        Position.valueOf("a1"), Position.valueOf("b1"), new Blank(Position.valueOf("b1")), EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("b1"), new Blank(Position.valueOf("b1")), WEST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("d2"), new Blank(Position.valueOf("d2")), NORTH_EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("b2"), new Blank(Position.valueOf("b2")), NORTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("b2"), new Blank(Position.valueOf("b2")), SOUTH_WEST),
                Arguments.of(
                        Position.valueOf("c3"), Position.valueOf("d2"), new Blank(Position.valueOf("d2")), SOUTH_EAST)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing")
    @DisplayName("출발점 기물인 킹은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing(Position from, Piece targetPiece) {
        final King king = new King(PieceColor.WHITE, from);

        assertThatThrownBy(() -> king.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 방향으로 기물이 움직일 수 없습니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfKing() {
        return Stream.of(
                Arguments.of(Position.valueOf("b1"), new Blank(Position.valueOf("c3"))),
                Arguments.of(Position.valueOf("b1"), new Blank(Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("c2"), new Blank(Position.valueOf("e3"))),
                Arguments.of(Position.valueOf("c2"), new Blank(Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("b3"), new Blank(Position.valueOf("c1"))),
                Arguments.of(Position.valueOf("b3"), new Blank(Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("c2"), new Blank(Position.valueOf("e1"))),
                Arguments.of(Position.valueOf("c2"), new Blank(Position.valueOf("a1")))
        );
    }
}

package chess.domain.piece.piece;

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

import chess.domain.piece.Blank;
import chess.domain.piece.MovePath;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import chess.domain.board.Direction;
import chess.domain.board.Position;

class QueenTest {

    @Test
    @DisplayName("퀸을 생성할 수 있다.")
    void createValidPieceOfQueen() {
        assertThat(new Queen(PieceColor.WHITE, Position.valueOf("d1"))).isInstanceOf(Queen.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validQueenMovableTestSet")
    @DisplayName("퀸이 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfQueen(Position from, Position to, Direction direction) {
        final Queen queen = new Queen(PieceColor.WHITE, from);

        assertThat(queen.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validQueenMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("d1"), Position.valueOf("d3"), Direction.NORTH),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("d1"), Direction.SOUTH),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("a1"), Direction.WEST),
                Arguments.of(Position.valueOf("a1"), Position.valueOf("d1"), Direction.EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("f3"), Direction.NORTH_EAST),
                Arguments.of(Position.valueOf("d1"), Position.valueOf("b3"), Direction.NORTH_WEST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("f1"), Direction.SOUTH_EAST),
                Arguments.of(Position.valueOf("d3"), Position.valueOf("b1"), Direction.SOUTH_WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("validFindMovePathToTargetPieceFromTheSourcePieceOfQueen")
    @DisplayName("출발점 기물인 퀸은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void validFindMovePathToTargetPieceFromTheSourcePieceOfQueen(
            Position from, Position to, Piece targetPiece, Direction direction) {

        final Queen queen = new Queen(PieceColor.WHITE, from);

        assertThat(queen.findByMovePath(targetPiece)).isEqualTo(new MovePath(from, to, direction));
    }

    static Stream<Arguments> validFindMovePathToTargetPieceFromTheSourcePieceOfQueen() {
        return Stream.of(
                Arguments.of(
                        Position.valueOf("a1"), Position.valueOf("a3"), new Blank(Position.valueOf("a3")), NORTH),
                Arguments.of(
                        Position.valueOf("a3"), Position.valueOf("a1"), new Blank(Position.valueOf("a1")), SOUTH),
                Arguments.of(
                        Position.valueOf("a1"), Position.valueOf("c1"), new Blank(Position.valueOf("c1")), EAST),
                Arguments.of(
                        Position.valueOf("c1"), Position.valueOf("a1"), new Blank(Position.valueOf("a1")), WEST),
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
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfQueen")
    @DisplayName("출발점 기물인 킹은 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfQueen(Position from, Piece targetPiece) {
        final Queen queen = new Queen(PieceColor.WHITE, from);

        assertThatThrownBy(() -> queen.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("직선 또는 대각선 방향이 아닙니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfQueen() {
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

package chess.domain.piece.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static chess.domain.board.Direction.NORTH_EAST_EAST;
import static chess.domain.board.Direction.NORTH_NORTH_EAST;
import static chess.domain.board.Direction.NORTH_NORTH_WEST;
import static chess.domain.board.Direction.NORTH_WEST_WEST;
import static chess.domain.board.Direction.SOUTH_EAST_EAST;
import static chess.domain.board.Direction.SOUTH_SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_SOUTH_WEST;
import static chess.domain.board.Direction.SOUTH_WEST_WEST;

import chess.domain.piece.Blank;
import chess.domain.piece.Knight;
import chess.domain.piece.MovePath;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import chess.domain.board.Direction;
import chess.domain.board.Position;

class KnightTest {

    @Test
    void createValidPieceOfKnight() {
        assertThat(new Knight(PieceColor.WHITE, Position.valueOf("b1"))).isInstanceOf(Knight.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validKnightMovableTestSet")
    @DisplayName("나이트가 이동하는 방향을 구할 수 있다.")
    void validMovablePieceOfKnight(Position from, Position to, Direction direction) {
        final Knight knight = new Knight(PieceColor.WHITE, from);

        assertThat(knight.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validKnightMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b1"), Position.valueOf("c3"), NORTH_NORTH_EAST),
                Arguments.of(Position.valueOf("b1"), Position.valueOf("a3"), NORTH_NORTH_WEST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("e3"), NORTH_EAST_EAST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("a3"), NORTH_WEST_WEST),
                Arguments.of(Position.valueOf("b3"), Position.valueOf("c1"), SOUTH_SOUTH_EAST),
                Arguments.of(Position.valueOf("b3"), Position.valueOf("a1"), SOUTH_SOUTH_WEST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("e1"), SOUTH_EAST_EAST),
                Arguments.of(Position.valueOf("c2"), Position.valueOf("a1"), SOUTH_WEST_WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("validFindMovePathToTargetPieceFromTheSourcePieceOfKnight")
    @DisplayName("출발점 기물인 나이트는 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void validFindMovePathToTargetPieceFromTheSourcePieceOfKnight(
            Position from, Position to, Piece targetPiece, Direction direction) {

        final Knight knight = new Knight(PieceColor.WHITE, from);

        assertThat(knight.findByMovePath(targetPiece)).isEqualTo(new MovePath(from, to, direction));
    }

    static Stream<Arguments> validFindMovePathToTargetPieceFromTheSourcePieceOfKnight() {
        return Stream.of(
                Arguments.of(
                        Position.valueOf("b1"), Position.valueOf("c3"),
                        new Blank(Position.valueOf("c3")), NORTH_NORTH_EAST),
                Arguments.of(
                        Position.valueOf("b1"), Position.valueOf("a3"),
                        new Blank(Position.valueOf("a3")), NORTH_NORTH_WEST),
                Arguments.of(
                        Position.valueOf("c2"), Position.valueOf("e3"),
                        new Blank(Position.valueOf("e3")), NORTH_EAST_EAST),
                Arguments.of(
                        Position.valueOf("c2"), Position.valueOf("a3"),
                        new Blank(Position.valueOf("a3")), NORTH_WEST_WEST),
                Arguments.of(
                        Position.valueOf("b3"), Position.valueOf("c1"),
                        new Blank(Position.valueOf("c1")), SOUTH_SOUTH_EAST),
                Arguments.of(
                        Position.valueOf("b3"), Position.valueOf("a1"),
                        new Blank(Position.valueOf("a1")), SOUTH_SOUTH_WEST),
                Arguments.of(
                        Position.valueOf("c2"), Position.valueOf("e1"),
                        new Blank(Position.valueOf("e1")), SOUTH_EAST_EAST),
                Arguments.of(
                        Position.valueOf("c2"), Position.valueOf("a1"),
                        new Blank(Position.valueOf("a1")), SOUTH_WEST_WEST)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidFindMovePathToTargetPieceFromTheSourcePieceOfKnight")
    @DisplayName("출발점 기물인 나이트는 도착점 기물을 통해서 이동방향을 구할 수 있다.")
    void invalidFindMovePathToTargetPieceFromTheSourcePieceOfKnight(Position from, Piece targetPiece) {

        final Knight knight = new Knight(PieceColor.WHITE, from);

        assertThatThrownBy(() -> knight.findByMovePath(targetPiece))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 방향입니다.");
    }

    static Stream<Arguments> invalidFindMovePathToTargetPieceFromTheSourcePieceOfKnight() {
        return Stream.of(
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("a3"), new Blank(Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("a1"), new Blank(Position.valueOf("c1"))),
                Arguments.of(Position.valueOf("c1"), new Blank(Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("c1"), new Blank(Position.valueOf("e3"))),
                Arguments.of(Position.valueOf("c1"), new Blank(Position.valueOf("a3"))),
                Arguments.of(Position.valueOf("c3"), new Blank(Position.valueOf("a1"))),
                Arguments.of(Position.valueOf("c3"), new Blank(Position.valueOf("e1")))
        );
    }
}

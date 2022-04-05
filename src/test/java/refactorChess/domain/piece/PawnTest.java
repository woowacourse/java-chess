package refactorChess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static refactorChess.domain.board.Direction.NORTH;
import static refactorChess.domain.board.Direction.NORTH_EAST;
import static refactorChess.domain.board.Direction.NORTH_WEST;
import static refactorChess.domain.board.Direction.SOUTH;
import static refactorChess.domain.board.Direction.SOUTH_EAST;
import static refactorChess.domain.board.Direction.SOUTH_WEST;
import static refactorChess.domain.piece.PieceColor.BLACK;
import static refactorChess.domain.piece.PieceColor.NONE;
import static refactorChess.domain.piece.PieceColor.WHITE;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactorChess.domain.board.Direction;
import refactorChess.domain.board.Position;

class PawnTest {

    @Test
    @DisplayName("폰을 생성할 수 있다.")
    void createValidPieceOfPawn() {
        assertThat(new Pawn(WHITE, Position.valueOf("b2"))).isInstanceOf(Pawn.class);
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validWhitePawnMovableTestSet")
    @DisplayName("흰색 폰이 이동하는 방향을 구할 수 있다.")
    void validMovableWhitePieceOfPawn(Position from, Position to, Direction direction) {
        final Pawn pawn = new Pawn(WHITE, from);

        assertThat(pawn.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validWhitePawnMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b2"), Position.valueOf("b3"), NORTH),
                Arguments.of(Position.valueOf("b2"), Position.valueOf("b4"), NORTH),
                Arguments.of(Position.valueOf("b2"), Position.valueOf("c3"), NORTH_EAST),
                Arguments.of(Position.valueOf("b2"), Position.valueOf("a3"), NORTH_WEST)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validMovableDirectionsWhitePawnMovableTestSet")
    @DisplayName("흰색 폰이 이동할 수 있는 방향들을 구할 수 있다.")
    void validMovableDirectionsPieceOfWhitePawn(Position from, Piece piece, Direction direction) {
        final Pawn pawn = new Pawn(WHITE, from);

        assertThat(pawn.findByMovableDirection(piece, direction))
                .contains(NORTH, NORTH_EAST, NORTH_WEST);
    }

    static Stream<Arguments> validMovableDirectionsWhitePawnMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b2"), new Pawn(BLACK, Position.valueOf("c3")), NORTH_EAST),
                Arguments.of(Position.valueOf("b2"), new Pawn(BLACK, Position.valueOf("a3")), NORTH_WEST),
                Arguments.of(Position.valueOf("b2"), new Blank(NONE, Position.valueOf("b3")), NORTH),
                Arguments.of(Position.valueOf("b2"), new Blank(NONE, Position.valueOf("b4")), NORTH)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validBlackPawnMovableTestSet")
    @DisplayName("검은색 폰이 이동하는 방향을 구할 수 있다.")
    void validMovableBlackPieceOfPawn(Position from, Position to, Direction direction) {
        final Pawn pawn = new Pawn(BLACK, from);

        assertThat(pawn.findByDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> validBlackPawnMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b7"), Position.valueOf("b6"), Direction.SOUTH),
                Arguments.of(Position.valueOf("b7"), Position.valueOf("b5"), Direction.SOUTH),
                Arguments.of(Position.valueOf("b7"), Position.valueOf("c6"), SOUTH_EAST),
                Arguments.of(Position.valueOf("b7"), Position.valueOf("a6"), SOUTH_WEST)
        );
    }

    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("validMovableDirectionsBlackPawnMovableTestSet")
    @DisplayName("검은색 폰이 이동할 수 있는 방향들을 구할 수 있다.")
    void validMovableDirectionsPieceOfBlackPawn(Position from, Piece piece, Direction direction) {
        final Pawn pawn = new Pawn(BLACK, from);

        assertThat(pawn.findByMovableDirection(piece, direction))
                .contains(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }

    static Stream<Arguments> validMovableDirectionsBlackPawnMovableTestSet() {
        return Stream.of(
                Arguments.of(Position.valueOf("b7"), new Pawn(WHITE, Position.valueOf("c6")), SOUTH_EAST),
                Arguments.of(Position.valueOf("b7"), new Pawn(WHITE, Position.valueOf("a6")), SOUTH_WEST),
                Arguments.of(Position.valueOf("b7"), new Blank(NONE, Position.valueOf("b6")), SOUTH),
                Arguments.of(Position.valueOf("b7"), new Blank(NONE, Position.valueOf("b5")), SOUTH)
        );
    }
}

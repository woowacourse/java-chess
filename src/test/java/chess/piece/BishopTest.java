package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("주위에 비어있는 경우")
    void moveCheckTest(Position movePosition, boolean expected) {
        Position position = new Position(Row.THREE, Column.B);
        Bishop bishop = new Bishop(Color.BLACK, position);

        boolean canMove = bishop.canMove(movePosition, List.of());

        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> moveCheckTest() {
        return Stream.of(
                Arguments.of(
                        new Position(Row.FOUR, Column.C), true
                ),
                Arguments.of(
                        new Position(Row.FIVE, Column.D), true
                ),
                Arguments.of(
                        new Position(Row.SIX, Column.E), true
                ),
                Arguments.of(
                        new Position(Row.SEVEN, Column.F), true
                ),
                Arguments.of(
                        new Position(Row.EIGHT, Column.G), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.C), true
                ),
                Arguments.of(
                        new Position(Row.ONE, Column.D), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.FIVE, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.SIX, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.SEVEN, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.EIGHT, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.EIGHT, Column.E), false
                ),
                Arguments.of(
                        new Position(Row.EIGHT, Column.F), false
                )
        );
    }

    @Test
    @DisplayName("주위에 아군이 있으면 못 움직인다.")
    void canMoveTest2(){
        Position position = new Position(Row.THREE, Column.B);
        Bishop bishop = new Bishop(Color.BLACK, position);
        Position toPosition = new Position(Row.TWO, Column.C);
        Pawn pawn = new Pawn(Color.BLACK,toPosition);
        boolean canMove = bishop.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("주위에 적이면 이동가능")
    void canMoveTest3(){
        Position position = new Position(Row.THREE, Column.B);
        Bishop bishop = new Bishop(Color.BLACK, position);
        Position toPosition = new Position(Row.TWO, Column.C);
        Pawn pawn = new Pawn(Color.WHITE,toPosition);
        boolean canMove = bishop.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("중간에 누가 있으면 갈 수 없다.")
    void canMoveTest4(){
        Position position = new Position(Row.THREE, Column.B);
        Bishop bishop = new Bishop(Color.BLACK, position);
        Position pawnPosition = new Position(Row.TWO, Column.C);
        Position toPosition = new Position(Row.ONE, Column.D);
        Pawn pawn = new Pawn(Color.WHITE,pawnPosition);
        boolean canMove = bishop.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isFalse();
    }
}

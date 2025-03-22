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

class KnightTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("주위에 비어있는 경우")
    void moveCheckTest(Position movePosition, boolean expected){
        Position position = new Position(Row.THREE, Column.C);
        Knight knight = new Knight(Color.BLACK, position);

        boolean canMove = knight.canMove(movePosition, List.of());

        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> moveCheckTest() {
        return Stream.of(
                Arguments.of(
                        new Position(Row.FIVE, Column.D), true
                ),
                Arguments.of(
                        new Position(Row.FIVE, Column.B), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.E), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.E), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.ONE, Column.B), true
                ),
                Arguments.of(
                        new Position(Row.ONE, Column.D), true
                ),
                Arguments.of(
                        new Position(Row.SIX, Column.B), false
                ),
                Arguments.of(
                        new Position(Row.SEVEN, Column.D), false
                )
        );
    }

    @Test
    @DisplayName("주위에 아군이 있으면 못 움직인다.")
    void canMoveTest2(){
        Position position = new Position(Row.THREE, Column.C);
        Knight knight = new Knight(Color.BLACK, position);
        Position toPosition = new Position(Row.TWO, Column.A);
        Pawn pawn = new Pawn(Color.BLACK,toPosition);
        boolean canMove = knight.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("주위에 적이면 이동가능")
    void canMoveTest3(){
        Position position = new Position(Row.THREE, Column.C);
        Knight knight = new Knight(Color.BLACK, position);
        Position toPosition = new Position(Row.TWO, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,toPosition);
        boolean canMove = knight.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("중간에 누가 있어도 지나갈 수 있다.")
    void canMoveTest4(){
        Position position = new Position(Row.THREE, Column.C);
        Knight knight = new Knight(Color.BLACK, position);
        Position pawnPosition = new Position(Row.THREE, Column.B);
        Position toPosition = new Position(Row.TWO, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,pawnPosition);
        boolean canMove = knight.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isTrue();
    }
}
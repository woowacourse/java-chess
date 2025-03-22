package chess.piece;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("주위에 비어있는 경우")
    void moveCheckTest(Position movePosition, boolean expected){
        Position position = new Position(Row.THREE, Column.B);
        King king = new King(Color.BLACK,position);

        boolean canMove = king.canMove(movePosition, List.of());

        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> moveCheckTest() {
        return Stream.of(
                Arguments.of(
                        new Position(Row.TWO, Column.B), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.B), true
                ),
                Arguments.of(
                        new Position(Row.THREE, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.THREE, Column.C), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.FOUR, Column.C), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.A), true
                ),
                Arguments.of(
                        new Position(Row.TWO, Column.C), true
                ),
                Arguments.of(
                        new Position(Row.FIVE, Column.C), false
                ),
                Arguments.of(
                        new Position(Row.THREE, Column.D), false
                )
        );
    }

    @Test
    @DisplayName("주위에 아군이 있으면 못 움직인다.")
    void canMoveTest2(){
        Position position = new Position(Row.THREE, Column.B);
        King king = new King(Color.BLACK,position);
        Position toPosition = new Position(Row.TWO, Column.C);
        Pawn pawn = new Pawn(Color.BLACK,toPosition);
        boolean canMove = king.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isFalse();
    }

    @Test
    @DisplayName("주위에 적이면 이동가능")
    void canMoveTest3(){
        Position position = new Position(Row.THREE, Column.B);
        King king = new King(Color.BLACK,position);
        Position toPosition = new Position(Row.TWO, Column.C);
        Pawn pawn = new Pawn(Color.WHITE,toPosition);
        boolean canMove = king.canMove(toPosition, List.of(pawn));
        assertThat(canMove).isTrue();
    }
}
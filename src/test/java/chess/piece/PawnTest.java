package chess.piece;

import static org.assertj.core.api.Assertions.*;

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

class PawnTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("이동 가능한지 판별")
    void canMoveTest(Color color ,Position movePosition, boolean expected){
        Position position = new Position(Row.THREE, Column.A);
        Pawn pawn = new Pawn(color,position);

        boolean canMove = pawn.canMove(movePosition, List.of());

        assertThat(canMove).isEqualTo(expected);
    }

    public static Stream<Arguments> canMoveTest(){
        return Stream.of(
                Arguments.of(
                        Color.WHITE,new Position(Row.FIVE,Column.A),true
                ),
                Arguments.of(
                        Color.WHITE,new Position(Row.FOUR,Column.A),true
                ),
                Arguments.of(
                        Color.WHITE,new Position(Row.SIX,Column.A),false
                ),
                Arguments.of(
                        Color.WHITE,new Position(Row.TWO,Column.B),false
                ),
                Arguments.of(
                        Color.BLACK,new Position(Row.TWO,Column.A),true
                ),
                Arguments.of(
                        Color.BLACK,new Position(Row.ONE,Column.A),true
                ),
                Arguments.of(
                        Color.BLACK,new Position(Row.FOUR,Column.A),false
                ),
                Arguments.of(
                        Color.BLACK,new Position(Row.FIVE,Column.B),false
                )
        );
    }

    @Test
    @DisplayName("이동 테스트")
    void moveToTest(){
        Position position = new Position(Row.THREE, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,position);
        Position toPosition = new Position(Row.FIVE, Column.A);

        pawn.moveTo(toPosition,List.of());

        Position nowPosition = pawn.getPosition();
        assertThat(nowPosition).isEqualTo(toPosition);
    }

    @Test
    @DisplayName("이동 경로에 아군이 있으면 예외 발생")
    void moveToTestException(){
        Position position = new Position(Row.THREE, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,position);
        Position toPosition = new Position(Row.FIVE, Column.A);
        Pawn pawn1 = new Pawn(Color.WHITE,toPosition);

        assertThatThrownBy(()->pawn.moveTo(toPosition,List.of(pawn1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동 경로에 적이 있으면 대각선은 가능하다.")
    void moveToTest2(){
        Position position = new Position(Row.THREE, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,position);
        Position toPosition = new Position(Row.FOUR, Column.B);
        Pawn pawn1 = new Pawn(Color.BLACK,toPosition);

        boolean canMove = pawn.canMove(toPosition, List.of(pawn1));

        assertThat(canMove).isTrue();
    }

    @Test
    @DisplayName("이동 경로에 적이 없는데 대각선으로 못감")
    void moveToTestException2(){
        Position position = new Position(Row.THREE, Column.A);
        Pawn pawn = new Pawn(Color.WHITE,position);
        Position toPosition = new Position(Row.FOUR, Column.B);
        Pawn pawn1 = new Pawn(Color.WHITE,toPosition);


        boolean canMove = pawn.canMove(toPosition, List.of(pawn1));

        assertThat(canMove).isFalse();
    }
}
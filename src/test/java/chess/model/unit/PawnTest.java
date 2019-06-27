package chess.model.unit;

import chess.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    void WHITE_폰_초기값_이동_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.WHITE);
        Square square = Square.of(Column.Col_2, Row.Row_G);
        int distance = 2;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.N, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, true))).isEqualTo(
                new HashSet<>(squareNavigators));
    }

    @Test
    void WHITE_폰_초기값_아닐때_이동_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.WHITE);
        Square square = Square.of(Column.Col_3, Row.Row_G);
        int distance = 1;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.N, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, true))).isEqualTo(
                new HashSet<>(squareNavigators));
    }

    @Test
    void BLACK_폰_초기값_이동_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.BLACK);
        Square square = Square.of(Column.Col_7, Row.Row_G);
        int distance = 2;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.S, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, true))).isEqualTo(
                new HashSet<>(squareNavigators));
    }

    @Test
    void BLACK_폰_초기값_아닐때_이동_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.BLACK);
        Square square = Square.of(Column.Col_6, Row.Row_G);
        int distance = 1;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.S, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, true))).isEqualTo(
                new HashSet<>(squareNavigators));
    }

    @Test
    void WHITE_폰_공격_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.WHITE);
        Square square = Square.of(Column.Col_3, Row.Row_G);
        int distance = 1;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.NE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.NW, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, false))).isEqualTo(
                new HashSet<>(squareNavigators));
    }

    @Test
    void BLACK_폰_공격_Navigator_생성_테스트() {
        Piece piece = new Pawn(Side.BLACK);
        Square square = Square.of(Column.Col_6, Row.Row_G);
        int distance = 1;
        List<SquareNavigator> squareNavigators = new ArrayList<>();
        squareNavigators.add(new SquareNavigator(Direction.SE, square, distance));
        squareNavigators.add(new SquareNavigator(Direction.SW, square, distance));
        assertThat(new HashSet<>(((Pawn) piece).findSquareNavigators(square, false))).isEqualTo(
                new HashSet<>(squareNavigators));
    }
}

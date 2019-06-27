package chess.model;

import chess.model.board.Board;
import chess.model.unit.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SquareNavigatorTest {
    @Test
    void 최소거리_값을_입력했을때_에러가_발생하지_않는지_테스트() {
        assertDoesNotThrow(() -> new SquareNavigator(Direction.NW, Square.of(Column.Col_5, Row.Row_D), 1));
    }

    @Test
    void 최소거리보다_작은_값을_입력했을때_에러_테스트() {
        assertThrows(InvalidRangeException.class, () -> new SquareNavigator(Direction.NW, Square.of(Column.Col_5, Row.Row_D), 0));
    }

    @Test
    void 한_방향으로_더이상_갈수_없는_좌표일때_빈_리스트를_반환하는지_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column.Col_1, Row.Row_H), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column.Col_1, Row.Row_H), 1);
        assertThat(navigator.findSquares(board)).isEqualTo(new ArrayList<>());
    }


    @Test
    void 한_방향의_이동할_거리가_한칸일_때_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column.Col_1, Row.Row_G), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column.Col_1, Row.Row_G), 1);
        assertThat(navigator.findSquares(board)).isEqualTo(Arrays.asList(Square.of(Column.Col_1, Row.Row_H)));
    }

    @Test
    void 한_방향으로_들르는_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column.Col_1, Row.Row_A), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column.Col_1, Row.Row_A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column.Col_1, Row.Row_B), Square.of(Column.Col_1, Row.Row_C), Square.of(Column.Col_1, Row.Row_D)
                        , Square.of(Column.Col_1, Row.Row_E), Square.of(Column.Col_1, Row.Row_F), Square.of(Column.Col_1, Row.Row_G)
                        , Square.of(Column.Col_1, Row.Row_H)));
    }

    @Test
    void 다른_팀의_말을_관통하는_경우_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column.Col_1, Row.Row_A), new Bishop(Side.WHITE));
            map.put(Square.of(Column.Col_4, Row.Row_D), new Pawn(Side.BLACK));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.NE, Square.of(Column.Col_1, Row.Row_A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column.Col_2, Row.Row_B), Square.of(Column.Col_3, Row.Row_C), Square.of(Column.Col_4, Row.Row_D)));
    }

    @Test
    void 같은_팀의_말을_관통하는_경우_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column.Col_1, Row.Row_A), new Bishop(Side.WHITE));
            map.put(Square.of(Column.Col_4, Row.Row_D), new Pawn(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.NE, Square.of(Column.Col_1, Row.Row_A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column.Col_2, Row.Row_B), Square.of(Column.Col_3, Row.Row_C)));
    }
}
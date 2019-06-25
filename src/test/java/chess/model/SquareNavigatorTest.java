package chess.model;

import chess.model.board.Board;
import chess.model.unit.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareNavigatorTest {
    @Test
    void 한_방향으로_더이상_갈수_없는_좌표일때_빈_리스트를_반환하는지_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.H), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column._1, Row.H), 1);
        assertThat(navigator.findSquares(board)).isEqualTo(new ArrayList<>());
    }


    @Test
    void 한_방향의_이동할_거리가_한칸일_때_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.G), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column._1, Row.G), 1);
        assertThat(navigator.findSquares(board)).isEqualTo(Arrays.asList(Square.of(Column._1, Row.H)));
    }

    @Test
    void 한_방향으로_들르는_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.A), new Rook(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.E, Square.of(Column._1, Row.A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column._1, Row.B), Square.of(Column._1, Row.C), Square.of(Column._1, Row.D)
                        , Square.of(Column._1, Row.E), Square.of(Column._1, Row.F), Square.of(Column._1, Row.G)
                        , Square.of(Column._1, Row.H)));
    }

    @Test
    void 다른_팀의_말을_관통하는_경우_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.A), new Bishop(Side.WHITE));
            map.put(Square.of(Column._4, Row.D), new Pawn(Side.BLACK));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.NE, Square.of(Column._1, Row.A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column._2, Row.B), Square.of(Column._3, Row.C), Square.of(Column._4, Row.D)));
    }

    @Test
    void 같은_팀의_말을_관통하는_경우_Square_리스트_테스트() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.A), new Bishop(Side.WHITE));
            map.put(Square.of(Column._4, Row.D), new Pawn(Side.WHITE));
            return map;
        });
        SquareNavigator navigator = new SquareNavigator(Direction.NE, Square.of(Column._1, Row.A), Integer.MAX_VALUE);
        assertThat(navigator.findSquares(board))
                .isEqualTo(Arrays.asList(
                        Square.of(Column._2, Row.B), Square.of(Column._3, Row.C)));
    }
}
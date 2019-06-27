package chess.model;

import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.unit.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveHandlerTest {
    @Test
    void 이동하려는_좌표에_말이_없을_때_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column.Col_5, Row.Row_H), Square.of(Column.Col_6, Row.Row_H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_6, Row.Row_D), Square.of(Column.Col_6, Row.Row_E), Side.BLACK)).isFalse();
    }

    @Test
    void 같은_위치로_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column.Col_1, Row.Row_H), Square.of(Column.Col_1, Row.Row_H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_8, Row.Row_D), Square.of(Column.Col_8, Row.Row_D), Side.BLACK)).isFalse();
    }

    @Test
    void 같은색_말로_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column.Col_1, Row.Row_H), Square.of(Column.Col_2, Row.Row_H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_8, Row.Row_D), Square.of(Column.Col_7, Row.Row_C), Side.BLACK)).isFalse();
    }

    @Test
    void 초기값_흰색_폰_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column.Col_2, Row.Row_D), Square.of(Column.Col_4, Row.Row_D), Side.WHITE)).isTrue();
        assertThat(handler.move(Square.of(Column.Col_2, Row.Row_D), Square.of(Column.Col_5, Row.Row_D), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_2, Row.Row_F), Square.of(Column.Col_3, Row.Row_E), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_2, Row.Row_F), Square.of(Column.Col_3, Row.Row_F), Side.WHITE)).isTrue();
    }

    @Test
    void 초기값_검은색_폰_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column.Col_7, Row.Row_D), Square.of(Column.Col_5, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(Square.of(Column.Col_7, Row.Row_D), Square.of(Column.Col_4, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_7, Row.Row_F), Square.of(Column.Col_6, Row.Row_E), Side.BLACK)).isFalse();
        assertThat(handler.move(Square.of(Column.Col_7, Row.Row_F), Square.of(Column.Col_6, Row.Row_F), Side.BLACK)).isTrue();
    }

    @Test
    void 검정_폰_공격상황_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_5, Row.Row_D), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_4, Row.Row_E), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_3, Row.Row_C), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_3, Row.Row_E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_E), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_C), Side.BLACK)).isTrue();
    }

    @Test
    void 흰색_폰_공격상황_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Pawn(Side.WHITE));
            map.put(Square.of(Column.Col_6, Row.Row_F), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_5, Row.Row_C), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_5, Row.Row_E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_F), Side.WHITE)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_D), Side.WHITE)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_C), Side.WHITE)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_E), Side.WHITE)).isTrue();
    }

    @Test
    void 비숍_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Bishop(Side.BLACK));
            map.put(Square.of(Column.Col_6, Row.Row_F), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_2, Row.Row_B), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_6, Row.Row_B), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_1, Row.Row_G), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_1, Row.Row_G), Side.BLACK)).isFalse();
    }

    @Test
    void 킹_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new King(Side.BLACK));
            map.put(Square.of(Column.Col_5, Row.Row_D), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_4, Row.Row_E), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_3, Row.Row_C), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_3, Row.Row_E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_E), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_B), Side.BLACK)).isFalse();
    }

    @Test
    void 퀸_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Queen(Side.BLACK));
            map.put(Square.of(Column.Col_6, Row.Row_D), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_4, Row.Row_F), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_2, Row.Row_B), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_2, Row.Row_F), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_G), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_1, Row.Row_A), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_E), Side.BLACK)).isTrue();
    }

    @Test
    void 나이트_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Knight(Side.BLACK));
            map.put(Square.of(Column.Col_5, Row.Row_B), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_6, Row.Row_E), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_2, Row.Row_C), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_3, Row.Row_F), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_B), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_5, Row.Row_F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_3, Row.Row_B), Side.BLACK)).isTrue();
    }

    @Test
    void 룩_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column.Col_4, Row.Row_D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Rook(Side.BLACK));
            map.put(Square.of(Column.Col_7, Row.Row_D), new Pawn(Side.BLACK));
            map.put(Square.of(Column.Col_2, Row.Row_D), new Queen(Side.WHITE));
            map.put(Square.of(Column.Col_4, Row.Row_F), new Knight(Side.WHITE));
            map.put(Square.of(Column.Col_4, Row.Row_B), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column.Col_7, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_2, Row.Row_D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_1, Row.Row_D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_G), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_B), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column.Col_4, Row.Row_C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column.Col_6, Row.Row_B), Side.BLACK)).isFalse();
    }
}
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
        assertThat(handler.move(Square.of(Column._5, Row.H), Square.of(Column._6, Row.H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column._6, Row.D), Square.of(Column._6, Row.E), Side.BLACK)).isFalse();
    }

    @Test
    void 같은_위치로_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column._1, Row.H), Square.of(Column._1, Row.H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column._8, Row.D), Square.of(Column._8, Row.D), Side.BLACK)).isFalse();
    }

    @Test
    void 같은색_말로_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column._1, Row.H), Square.of(Column._2, Row.H), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column._8, Row.D), Square.of(Column._7, Row.C), Side.BLACK)).isFalse();
    }

    @Test
    void 초기값_흰색_폰_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column._2, Row.D), Square.of(Column._4, Row.D), Side.WHITE)).isTrue();
        assertThat(handler.move(Square.of(Column._2, Row.D), Square.of(Column._5, Row.D), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column._2, Row.F), Square.of(Column._3, Row.E), Side.WHITE)).isFalse();
        assertThat(handler.move(Square.of(Column._2, Row.F), Square.of(Column._3, Row.F), Side.WHITE)).isTrue();
    }

    @Test
    void 초기값_검은색_폰_이동_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(Square.of(Column._7, Row.D), Square.of(Column._5, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(Square.of(Column._7, Row.D), Square.of(Column._4, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(Square.of(Column._7, Row.F), Square.of(Column._6, Row.E), Side.BLACK)).isFalse();
        assertThat(handler.move(Square.of(Column._7, Row.F), Square.of(Column._6, Row.F), Side.BLACK)).isTrue();
    }

    @Test
    void 검정_폰_공격상황_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Pawn(Side.BLACK));
            map.put(Square.of(Column._5, Row.D), new Pawn(Side.BLACK));
            map.put(Square.of(Column._4, Row.E), new Queen(Side.WHITE));
            map.put(Square.of(Column._3, Row.C), new Knight(Side.WHITE));
            map.put(Square.of(Column._3, Row.E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._2, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._3, Row.E), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.C), Side.BLACK)).isTrue();
    }

    @Test
    void 흰색_폰_공격상황_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Pawn(Side.WHITE));
            map.put(Square.of(Column._6, Row.F), new Pawn(Side.BLACK));
            map.put(Square.of(Column._5, Row.C), new Knight(Side.WHITE));
            map.put(Square.of(Column._5, Row.E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._6, Row.F), Side.WHITE)).isFalse();
        assertThat(handler.move(square, Square.of(Column._5, Row.D), Side.WHITE)).isTrue();
        assertThat(handler.move(square, Square.of(Column._5, Row.C), Side.WHITE)).isFalse();
        assertThat(handler.move(square, Square.of(Column._5, Row.E), Side.WHITE)).isTrue();
    }

    @Test
    void 비숍_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Bishop(Side.BLACK));
            map.put(Square.of(Column._6, Row.F), new Pawn(Side.BLACK));
            map.put(Square.of(Column._2, Row.B), new Queen(Side.WHITE));
            map.put(Square.of(Column._6, Row.B), new Knight(Side.WHITE));
            map.put(Square.of(Column._1, Row.G), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._5, Row.E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._2, Row.B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._5, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._2, Row.F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._1, Row.G), Side.BLACK)).isFalse();
    }

    @Test
    void 킹_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new King(Side.BLACK));
            map.put(Square.of(Column._5, Row.D), new Pawn(Side.BLACK));
            map.put(Square.of(Column._4, Row.E), new Queen(Side.WHITE));
            map.put(Square.of(Column._3, Row.C), new Knight(Side.WHITE));
            map.put(Square.of(Column._3, Row.E), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._5, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._4, Row.E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._3, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._3, Row.E), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._5, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._4, Row.B), Side.BLACK)).isFalse();
    }

    @Test
    void 퀸_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Queen(Side.BLACK));
            map.put(Square.of(Column._6, Row.D), new Pawn(Side.BLACK));
            map.put(Square.of(Column._4, Row.F), new Queen(Side.WHITE));
            map.put(Square.of(Column._2, Row.B), new Knight(Side.WHITE));
            map.put(Square.of(Column._2, Row.F), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._6, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._5, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._4, Row.F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._4, Row.G), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._2, Row.B), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._1, Row.A), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._2, Row.F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.E), Side.BLACK)).isTrue();
    }

    @Test
    void 나이트_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Knight(Side.BLACK));
            map.put(Square.of(Column._5, Row.B), new Pawn(Side.BLACK));
            map.put(Square.of(Column._6, Row.E), new Queen(Side.WHITE));
            map.put(Square.of(Column._2, Row.C), new Knight(Side.WHITE));
            map.put(Square.of(Column._3, Row.F), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._5, Row.B), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._5, Row.F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._2, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._2, Row.E), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._3, Row.F), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._3, Row.B), Side.BLACK)).isTrue();
    }

    @Test
    void 룩_이동_테스트() {
        Board board = new Board();
        Square square = Square.of(Column._4, Row.D);
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(square, new Rook(Side.BLACK));
            map.put(Square.of(Column._7, Row.D), new Pawn(Side.BLACK));
            map.put(Square.of(Column._2, Row.D), new Queen(Side.WHITE));
            map.put(Square.of(Column._4, Row.F), new Knight(Side.WHITE));
            map.put(Square.of(Column._4, Row.B), new Bishop(Side.BLACK));
            return map;
        });
        MoveHandler handler = new MoveHandler(board);
        assertThat(handler.move(square, Square.of(Column._7, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._6, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._2, Row.D), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._1, Row.D), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._4, Row.F), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._4, Row.G), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._4, Row.B), Side.BLACK)).isFalse();
        assertThat(handler.move(square, Square.of(Column._4, Row.C), Side.BLACK)).isTrue();
        assertThat(handler.move(square, Square.of(Column._6, Row.B), Side.BLACK)).isFalse();
    }
}
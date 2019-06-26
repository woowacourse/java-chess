package chess.model.board;

import chess.model.Column;
import chess.model.Row;
import chess.model.Square;
import chess.model.unit.*;
import chess.view.OutputView;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    @Test
    void 빈_보드_테스트() {
        Board board = new Board();
        board.initialize(new EmptyBoardInitializer());
        assertThat(OutputView.board(board))
                .isEqualTo(
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n");
    }

    @Test
    void 기본_보드_테스트() {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        assertThat(OutputView.board(board))
                .isEqualTo(
                    "RNBQKBNR\n" +
                    "PPPPPPPP\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "pppppppp\n" +
                    "rnbqkbnr\n");
    }

    @Test
    void createPieceMapTest() {
        Board board = new Board();
        board.initialize(() -> {
            Map<Square, Piece> map = new HashMap<>();
            map.put(Square.of(Column._1, Row.D), new Queen(Side.BLACK));
            map.put(Square.of(Column._2, Row.H), new Pawn(Side.WHITE));
            map.put(Square.of(Column._3, Row.B), new King(Side.BLACK));
            map.put(Square.of(Column._4, Row.C), new Knight(Side.WHITE));
            map.put(Square.of(Column._5, Row.F), new Bishop(Side.BLACK));
            map.put(Square.of(Column._7, Row.E), new Rook(Side.WHITE));
            return map;
        });
        Map<String, String> pieceMap = board.createPieceMap();
        assertThat(pieceMap).containsEntry("d1","bQ");
        assertThat(pieceMap).containsEntry("h2","wP");
        assertThat(pieceMap).containsEntry("b3","bK");
        assertThat(pieceMap).containsEntry("c4","wN");
        assertThat(pieceMap).containsEntry("f5","bB");
        assertThat(pieceMap).containsEntry("e7","wR");
    }
}

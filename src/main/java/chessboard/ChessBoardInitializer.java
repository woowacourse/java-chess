package chessboard;

import coordinate.Coordinate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import piece.Bishop;
import piece.Color;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;
import position.Column;
import position.Row;

public class ChessBoardInitializer {

    private static final int BLACK_FIRST_RANK = 0;
    private static final int BLACK_SECOND_RANK = 1;
    private static final int WHITE_FIRST_RANK = 7;
    private static final int WHITE_SECOND_RANK = 6;
    private static final int CHESS_BOARD_SIZE = 8;

    public static Map<Coordinate, Piece> createInitialBoard() {
        Map<Coordinate, Piece> board = new HashMap<>();
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            board.put(new Coordinate(new Row(BLACK_FIRST_RANK), new Column(i)), createFirstRank(Color.BLACK).get(i));
            board.put(new Coordinate(new Row(WHITE_FIRST_RANK), new Column(i)), createFirstRank(Color.WHITE).get(i));
        }
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            board.put(new Coordinate(new Row(BLACK_SECOND_RANK), new Column(i)), new Pawn(Color.BLACK));
            board.put(new Coordinate(new Row(WHITE_SECOND_RANK), new Column(i)), new Pawn(Color.WHITE));
        }
        return board;
    }

    private static List<Piece> createFirstRank(Color color) {
        return List.of(
                new Rook(color),
                new Knight(color),
                new Bishop(color),
                new Queen(color),
                new King(color),
                new Bishop(color),
                new Knight(color),
                new Rook(color)
        );
    }
}

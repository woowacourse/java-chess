package chessboard;

import coordinate.Coordinate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import piece.Bishop;
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
            board.put(new Coordinate(new Row(BLACK_FIRST_RANK), new Column(i)), createFirstRank(true).get(i));
            board.put(new Coordinate(new Row(WHITE_FIRST_RANK), new Column(i)), createFirstRank(false).get(i));
        }
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            board.put(new Coordinate(new Row(BLACK_SECOND_RANK), new Column(i)), new Pawn(true));
            board.put(new Coordinate(new Row(WHITE_SECOND_RANK), new Column(i)), new Pawn(false));
        }
        return board;
    }

    private static List<Piece> createFirstRank(boolean isBlack) {
        return List.of(
                new Rook(isBlack),
                new Knight(isBlack),
                new Bishop(isBlack),
                new Queen(isBlack),
                new King(isBlack),
                new Bishop(isBlack),
                new Knight(isBlack),
                new Rook(isBlack)
        );
    }
}

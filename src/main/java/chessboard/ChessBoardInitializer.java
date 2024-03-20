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

    public static Map<Coordinate, Piece> createInitialBoard() {
        Map<Coordinate, Piece> board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            board.put(new Coordinate(new Row(0), new Column(i)), createFirstRank(true).get(i));
            board.put(new Coordinate(new Row(7), new Column(i)), createFirstRank(false).get(i));
        }
        for (int i = 0; i < 8; i++) {
            board.put(new Coordinate(new Row(1), new Column(i)), new Pawn(true));
            board.put(new Coordinate(new Row(6), new Column(i)), new Pawn(false));
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

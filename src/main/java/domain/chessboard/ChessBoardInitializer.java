package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardInitializer {

    private static final int BLACK_FIRST_RANK = 0;
    private static final int BLACK_SECOND_RANK = 1;
    private static final int WHITE_FIRST_RANK = 7;
    private static final int WHITE_SECOND_RANK = 6;
    private static final int CHESS_BOARD_SIZE = 8;

    public static Map<Coordinate, ChessPiece> createInitialBoard() {
        Map<Coordinate, ChessPiece> board = new HashMap<>();

        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            board.put(new Coordinate(BLACK_FIRST_RANK, i), createFirstRank(Color.BLACK).get(i));
            board.put(new Coordinate(WHITE_FIRST_RANK, i), createFirstRank(Color.WHITE).get(i));
        }
        for (int i = 0; i < CHESS_BOARD_SIZE; i++) {
            board.put(new Coordinate(BLACK_SECOND_RANK, i), new BlackPawn());
            board.put(new Coordinate(WHITE_SECOND_RANK, i), new WhitePawn());
        }
        return board;
    }

    private static List<ChessPiece> createFirstRank(Color color) {
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

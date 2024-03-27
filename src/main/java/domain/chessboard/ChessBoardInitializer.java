package domain.chessboard;

import domain.coordinate.Coordinate;
import domain.piece.Bishop;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.base.ChessPiece;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardInitializer {

    private static final String BLACK_FIRST_RANK = "8";
    private static final String BLACK_SECOND_RANK = "7";
    private static final String WHITE_FIRST_RANK = "1";
    private static final String WHITE_SECOND_RANK = "2";
    private static final String CHESS_BOARD_COLUMNS = "abcdefgh";

    public static Map<Coordinate, ChessPiece> createInitialBoard() {
        Map<Coordinate, ChessPiece> board = new LinkedHashMap<>();

        settingBlackFirstRow(board);
        settingSecondBlackRow(board);
        settingAllBlankRows(board);
        settingSecondWhiteRow(board);
        settingFirstWhiteRow(board);

        return board;
    }

    private static void settingBlackFirstRow(Map<Coordinate, ChessPiece> board) {
        for (int i = 1; i <= CHESS_BOARD_COLUMNS.length(); i++) {
            board.put(Coordinate.from(CHESS_BOARD_COLUMNS.charAt(i - 1) + BLACK_FIRST_RANK),
                    createFirstRank(Color.BLACK).get(i));
        }
    }

    private static void settingAllBlankRows(Map<Coordinate, ChessPiece> board) {
        for (int row = 6; row >= 3; row--) {
            settingBlankRow(board, String.valueOf(row));
        }
    }

    private static void settingBlankRow(Map<Coordinate, ChessPiece> board, String row) {
        for (int i = 1; i <= CHESS_BOARD_COLUMNS.length(); i++) {
            board.put(Coordinate.from(CHESS_BOARD_COLUMNS.charAt(i - 1) + row), new Blank());
        }
    }

    private static void settingSecondBlackRow(Map<Coordinate, ChessPiece> board) {
        for (int i = 1; i <= CHESS_BOARD_COLUMNS.length(); i++) {
            board.put(Coordinate.from(CHESS_BOARD_COLUMNS.charAt(i - 1) + BLACK_SECOND_RANK), new BlackPawn());
        }
    }

    private static void settingSecondWhiteRow(Map<Coordinate, ChessPiece> board) {
        for (int i = 1; i <= CHESS_BOARD_COLUMNS.length(); i++) {
            board.put(Coordinate.from(CHESS_BOARD_COLUMNS.charAt(i - 1) + WHITE_SECOND_RANK), new WhitePawn());
        }
    }

    private static void settingFirstWhiteRow(Map<Coordinate, ChessPiece> board) {
        for (int i = 1; i <= CHESS_BOARD_COLUMNS.length(); i++) {
            board.put(Coordinate.from(CHESS_BOARD_COLUMNS.charAt(i - 1) + WHITE_FIRST_RANK),
                    createFirstRank(Color.WHITE).get(i));
        }
    }


    private static List<ChessPiece> createFirstRank(Color color) {
        return List.of(
                new Blank(),
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

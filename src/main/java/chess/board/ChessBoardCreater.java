package chess.board;

import chess.location.Location;
import chess.piece.type.*;
import chess.team.Team;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardCreater {
    private static final int MINIMUM_LINE = 0;
    private static final int LIMIT_LINE = 8;
    public static final char COL_START = 'a';
    public static final char COL_END = 'h';
    private static final int WHITE_NOBLE_LINE = 1;
    private static final int WHITE_PAWN_LINE = 2;
    private static final int BLACK_PAWN_LINE = 7;
    private static final int BLACK_NOBLE_LINE = 8;

    public static ChessBoard create() {
        Map<Location, Piece> board = new HashMap<>();
        putNoble(board, WHITE_NOBLE_LINE, Team.WHITE);
        putPawns(board, WHITE_PAWN_LINE, Team.WHITE);
        putPawns(board, BLACK_PAWN_LINE, Team.BLACK);
        putNoble(board, BLACK_NOBLE_LINE, Team.BLACK);
        return new ChessBoard(board);
    }

    private static void putNoble(Map<Location, Piece> board, int row, Team team) {
        board.put(new Location(row, 'a'), new Rook(team));
        board.put(new Location(row, 'b'), new Knight(team));
        board.put(new Location(row, 'c'), new Bishop(team));
        board.put(new Location(row, 'd'), new Queen(team));
        board.put(new Location(row, 'e'), new King(team));
        board.put(new Location(row, 'f'), new Bishop(team));
        board.put(new Location(row, 'g'), new Knight(team));
        board.put(new Location(row, 'h'), new Rook(team));
    }

    private static void putPawns(Map<Location, Piece> board, int row, Team team) {
        for (int i = MINIMUM_LINE; i < LIMIT_LINE; i++) {
            board.put(new Location(row, (char) (i + COL_START)), new Pawn(team));
        }
    }
}

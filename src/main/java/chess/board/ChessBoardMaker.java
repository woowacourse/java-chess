package chess.board;

import chess.location.Location;
import chess.piece.type.*;
import chess.team.Team;

import java.util.Map;

class ChessBoardMaker {
    public static final int MINIMUM_LINE = 0;
    public static final int LIMIT_LINE = 8;
    private static final char COL_START = 'a';

    static void putNoble(Map<Location, Piece> board, int row, Team team) {
        board.put(new Location(row, 'a'), new Rook(team));
        board.put(new Location(row, 'b'), new Knight(team));
        board.put(new Location(row, 'c'), new Bishop(team));
        board.put(new Location(row, 'd'), new Queen(team));
        board.put(new Location(row, 'e'), new King(team));
        board.put(new Location(row, 'f'), new Bishop(team));
        board.put(new Location(row, 'g'), new Knight(team));
        board.put(new Location(row, 'h'), new Rook(team));
    }

    static void putPawns(Map<Location, Piece> board, int row, Team team) {
        for (int i = MINIMUM_LINE; i < LIMIT_LINE; i++) {
            board.put(new Location(row, (char) (i + COL_START)), new Pawn(team));
        }
    }
}

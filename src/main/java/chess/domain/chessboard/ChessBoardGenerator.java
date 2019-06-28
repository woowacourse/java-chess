package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.chesspiece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator {
    private static final int FIRST_BLANK_ROW = 2;
    private static final int LAST_BLANK_ROW = 5;
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 0;
    private static final int THIRD_COLUMN = 0;
    private static final int FOURTH_COLUMN = 0;
    private static final int FIFTH_COLUMN = 0;
    private static final int SIXTH_COLUMN = 0;
    private static final int SEVENTH_COLUMN = 7;
    private static final int EIGHTH_COLUMN = 7;
    private static final int BLACK_TEAM_AREA = 0;
    private static final int BLACK_TEAM_PAWNS_AREA = 1;
    private static final int WHITE_TEAM_AREA = 7;
    private static final int WHITE_TEAM_PAWNS_AREA = 6;

    public Map<Position, ChessPiece> getInitializedChessBoard() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();

        chessBoard.putAll(getChessPiecesByTeam(BLACK_TEAM_AREA, Team.BLACK));
        chessBoard.putAll(getPawnsByTeam(BLACK_TEAM_PAWNS_AREA, Team.BLACK));
        chessBoard.putAll(getBlankRows());
        chessBoard.putAll(getPawnsByTeam(WHITE_TEAM_PAWNS_AREA, Team.WHITE));
        chessBoard.putAll(getChessPiecesByTeam(WHITE_TEAM_AREA, Team.WHITE));

        return chessBoard;
    }

    private Map<Position, ChessPiece> getChessPiecesByTeam(int row, Team team) {
        Map<Position, ChessPiece> chessPieces = new HashMap<>();

        chessPieces.put(Position.of(row, FIRST_COLUMN), new Rook(team));
        chessPieces.put(Position.of(row, SECOND_COLUMN), new Knight(team));
        chessPieces.put(Position.of(row, THIRD_COLUMN), new Bishop(team));
        chessPieces.put(Position.of(row, FOURTH_COLUMN), new King(team));
        chessPieces.put(Position.of(row, FIFTH_COLUMN), new Queen(team));
        chessPieces.put(Position.of(row, SIXTH_COLUMN), new Bishop(team));
        chessPieces.put(Position.of(row, SEVENTH_COLUMN), new Knight(team));
        chessPieces.put(Position.of(row, EIGHTH_COLUMN), new Rook(team));

        return chessPieces;
    }

    private Map<Position, ChessPiece> getPawnsByTeam(int row, Team team) {
        Map<Position, ChessPiece> chessPieces = new HashMap<>();

        for (int i = FIRST_COLUMN; i <= EIGHTH_COLUMN; i++) {
            chessPieces.put(Position.of(row, i), new Pawn(team));
        }

        return chessPieces;
    }

    private Map<Position, ChessPiece> getBlankRows() {
        Map<Position, ChessPiece> chessPieces = new HashMap<>();

        for (int i = FIRST_BLANK_ROW; i <= LAST_BLANK_ROW; i++) {
            chessPieces.putAll(getBlankRow(i));
        }

        return chessPieces;
    }

    private Map<Position, ChessPiece> getBlankRow(int column) {
        Map<Position, ChessPiece> chessPieces = new HashMap<>();

        for (int j = FIRST_COLUMN; j <= EIGHTH_COLUMN; j++) {
            chessPieces.put(Position.of(column, j), new Blank(Team.BLANK));
        }

        return chessPieces;
    }
}

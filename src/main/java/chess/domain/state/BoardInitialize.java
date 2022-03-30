package chess.domain.state;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;
import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class BoardInitialize {
    private static final Map<Position, Piece> board = new HashMap<>();

    public static Map<Position, Piece> create() {
        initBordBlackPiece();
        initBoardWhitePiece();
        initBlanks();
        return board;
    }

    private static void initBordBlackPiece() {
        Row row = Row.EIGHT;
        board.put(new Position(Column.A, row), new Rook(Team.BLACK, new Position(Column.A, row)));
        board.put(new Position(Column.B, row), new Knight(Team.BLACK, new Position(Column.B, row)));
        board.put(new Position(Column.C, row), new Bishop(Team.BLACK, new Position(Column.C, row)));
        board.put(new Position(Column.D, row), new Queen(Team.BLACK, new Position(Column.D, row)));
        board.put(new Position(Column.E, row), new King(Team.BLACK, new Position(Column.E, row)));
        board.put(new Position(Column.F, row), new Bishop(Team.BLACK, new Position(Column.F, row)));
        board.put(new Position(Column.G, row), new Knight(Team.BLACK, new Position(Column.G, row)));
        board.put(new Position(Column.H, row), new Rook(Team.BLACK, new Position(Column.H, row)));
        initPawn(Team.BLACK, Row.SEVEN);
    }

    private static void initBoardWhitePiece() {
        Row row = Row.ONE;
        board.put(new Position(Column.A, row), new Rook(Team.WHITE, new Position(Column.A, row)));
        board.put(new Position(Column.B, row), new Knight(Team.WHITE, new Position(Column.B, row)));
        board.put(new Position(Column.C, row), new Bishop(Team.WHITE, new Position(Column.C, row)));
        board.put(new Position(Column.D, row), new Queen(Team.WHITE, new Position(Column.D, row)));
        board.put(new Position(Column.E, row), new King(Team.WHITE, new Position(Column.E, row)));
        board.put(new Position(Column.F, row), new Bishop(Team.WHITE, new Position(Column.F, row)));
        board.put(new Position(Column.G, row), new Knight(Team.WHITE, new Position(Column.G, row)));
        board.put(new Position(Column.H, row), new Rook(Team.WHITE, new Position(Column.H, row)));
        initPawn(Team.WHITE, Row.TWO);
    }

    private static void initPawn(Team team, Row row) {
        for (Column col : Column.values()) {
            Position position = new Position(col, row);
            board.put(position, new Pawn(team, position));
        }
    }

    private static void initBlanks() {
        initBlankLine(Row.THREE);
        initBlankLine(Row.FOUR);
        initBlankLine(Row.FIVE);
        initBlankLine(Row.SIX);
    }

    private static void initBlankLine(Row row) {
        for (Column col : Column.values()) {
            board.put(new Position(col, row), new Blank(Team.NONE, new Position(col, row)));
        }
    }
}

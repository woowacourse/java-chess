package chess.domain.state;

import chess.domain.Team;
import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class BoardInitialize {
    private static final Map<Position, Piece> BOARD = new HashMap<>();

    public static Map<Position, Piece> create() {
        initBordBlackPiece();
        initBoardWhitePiece();
        initBlanks();
        return BOARD;
    }

    private static void initBordBlackPiece() {
        Row row = Row.EIGHT;
        BOARD.put(Position.of(Column.A, row), new Rook(Team.BLACK));
        BOARD.put(Position.of(Column.B, row), new Knight(Team.BLACK));
        BOARD.put(Position.of(Column.C, row), new Bishop(Team.BLACK));
        BOARD.put(Position.of(Column.D, row), new Queen(Team.BLACK));
        BOARD.put(Position.of(Column.E, row), new King(Team.BLACK));
        BOARD.put(Position.of(Column.F, row), new Bishop(Team.BLACK));
        BOARD.put(Position.of(Column.G, row), new Knight(Team.BLACK));
        BOARD.put(Position.of(Column.H, row), new Rook(Team.BLACK));
        initPawn(Team.BLACK, Row.SEVEN);
    }

    private static void initBoardWhitePiece() {
        Row row = Row.ONE;
        BOARD.put(Position.of(Column.A, row), new Rook(Team.WHITE));
        BOARD.put(Position.of(Column.B, row), new Knight(Team.WHITE));
        BOARD.put(Position.of(Column.C, row), new Bishop(Team.WHITE));
        BOARD.put(Position.of(Column.D, row), new Queen(Team.WHITE));
        BOARD.put(Position.of(Column.E, row), new King(Team.WHITE));
        BOARD.put(Position.of(Column.F, row), new Bishop(Team.WHITE));
        BOARD.put(Position.of(Column.G, row), new Knight(Team.WHITE));
        BOARD.put(Position.of(Column.H, row), new Rook(Team.WHITE));
        initPawn(Team.WHITE, Row.TWO);
    }

    private static void initPawn(Team team, Row row) {
        for (Column col : Column.values()) {
            Position position = Position.of(col, row);
            BOARD.put(position, new Pawn(team));
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
            BOARD.put(Position.of(col, row), new Blank(Team.NONE));
        }
    }
}

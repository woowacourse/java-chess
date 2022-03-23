package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator {

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, Player.BLACK);
        createTeamBoard(board, Player.WHITE);
        return board;
    }


    private void createInitialize(Map<Position, Piece> board) {
        for (Row row : Row.values()) {
            initializeByRow(board, row);
        }
    }

    private void initializeByRow(Map<Position, Piece> board, Row row) {
        for (Column column : Column.values()) {
            board.put(new Position(row, column), null);
        }
    }

    private void createTeamBoard(final Map<Position, Piece> board, final Player player) {
        createInitPawn(board, player);
        createInitRook(board, player);
        createInitKnight(board, player);
        createInitBishop(board, player);
        createInitQueen(board, player);
        createInitKing(board, player);
    }

    private void createInitPawn(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            Arrays.stream(Column.values())
                .forEach(
                    column -> board.put(new Position(Row.SEVEN, column), new Pawn(Player.BLACK)));
            return;
        }
        Arrays.stream(Column.values())
            .forEach(column -> board.put(new Position(Row.TWO, column), new Pawn(Player.WHITE)));
    }

    private void createInitRook(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            board.put(new Position(Row.EIGHT, Column.A), new Rook(Player.BLACK));
            board.put(new Position(Row.EIGHT, Column.H), new Rook(Player.BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.A), new Rook(Player.WHITE));
        board.put(new Position(Row.ONE, Column.H), new Rook(Player.WHITE));
    }

    private void createInitKnight(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            board.put(new Position(Row.EIGHT, Column.B), new Knight(Player.BLACK));
            board.put(new Position(Row.EIGHT, Column.G), new Knight(Player.BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.B), new Knight(Player.WHITE));
        board.put(new Position(Row.ONE, Column.G), new Knight(Player.WHITE));
    }

    private void createInitBishop(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            board.put(new Position(Row.EIGHT, Column.C), new Bishop(Player.BLACK));
            board.put(new Position(Row.EIGHT, Column.F), new Bishop(Player.BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.C), new Bishop(Player.WHITE));
        board.put(new Position(Row.ONE, Column.F), new Bishop(Player.WHITE));
    }

    private void createInitQueen(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            board.put(new Position(Row.EIGHT, Column.D), new Queen(Player.BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.D), new Queen(Player.WHITE));
    }

    private void createInitKing(Map<Position, Piece> board, Player player) {
        if (player == Player.BLACK) {
            board.put(new Position(Row.EIGHT, Column.E), new King(Player.BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.E), new King(Player.WHITE));
    }
}

package domain;

import static domain.piece.Player.*;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Player;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Row;
import domain.position.Position;
import domain.position.Column;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ChessBoardGenerator implements BoardGenerator{

    @Override
    public Map<Position, Piece> generate() {
        Map<Position, Piece> board = new HashMap<>();
        createInitialize(board);
        createTeamBoard(board, BLACK);
        createTeamBoard(board, Player.WHITE);

        return board;
    }

    private void createInitialize(final Map<Position, Piece> board) {
        for(Column row : Column.values()){
            initializeByRow(board, row);
        }
    }

    private void initializeByRow(final Map<Position, Piece> board, final Column column) {
        for(Row row : Row.values()){
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
        if (player == BLACK){
            Arrays.stream(Column.values()).forEach(column -> board.put(new Position(Row.SEVEN, column), new Pawn(BLACK)));
            return;
        }
        Arrays.stream(Column.values()).forEach(column -> board.put(new Position(Row.TWO, column), new Pawn(WHITE)));
    }

    private void createInitRook(Map<Position, Piece> board, Player player) {
        if (player == BLACK){
            board.put(new Position(Row.EIGHT, Column.A), new Rook(BLACK));
            board.put(new Position(Row.EIGHT, Column.H), new Rook(BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.A), new Rook(WHITE));
        board.put(new Position(Row.ONE, Column.H), new Rook(WHITE));
    }

    private void createInitKnight(Map<Position, Piece> board, Player player) {
        if (player == BLACK){
            board.put(new Position(Row.EIGHT, Column.B), new Knight(BLACK));
            board.put(new Position(Row.EIGHT, Column.G), new Knight(BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.B), new Knight(WHITE));
        board.put(new Position(Row.ONE, Column.G), new Knight(WHITE));
    }

    private void createInitBishop(Map<Position, Piece> board, Player player) {
        if (player == BLACK){
            board.put(new Position(Row.EIGHT, Column.C), new Bishop(BLACK));
            board.put(new Position(Row.EIGHT, Column.F), new Bishop(BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.C), new Bishop(WHITE));
        board.put(new Position(Row.ONE, Column.F), new Bishop(WHITE));
    }

    private void createInitQueen(Map<Position, Piece> board, Player player) {
        if (player == BLACK){
            board.put(new Position(Row.EIGHT, Column.D), new Queen(BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.D), new Queen(WHITE));
    }

    private void createInitKing(Map<Position, Piece> board, Player player) {
        if (player == BLACK){
            board.put(new Position(Row.EIGHT, Column.E), new King(BLACK));
            return;
        }
        board.put(new Position(Row.ONE, Column.E), new King(WHITE));
    }
}

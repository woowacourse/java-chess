package chess.domain.board;

import chess.domain.location.Column;
import chess.domain.location.Location;
import chess.domain.location.Row;
import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Location, Piece> map;

    public static void main(String[] args) {
        Board board = new Board();
    }

    public Board() {
        this.map = new HashMap<>();
        initialSetting();

        for (Row value : Row.values()) {
            for (Column column : Column.values()) {
                System.out.print(map.get(new Location(column, value)) + " ");
            }
            System.out.println();
        }
    }

    private void initialSetting() {
        initialPawnSetting();
        initialRookSetting();
        initialKnightSetting();
        initialBishopSetting();
        initialQueenSetting();
        initialKingSetting();
    }


    private void initialPawnSetting() {
    }

    private void initialRookSetting() {
        map.put(new Location(Column.A, Row.ONE), new Rook(Color.BLACK));
        map.put(new Location(Column.A, Row.EIGHT), new Rook(Color.WHITE));
        map.put(new Location(Column.H, Row.ONE), new Rook(Color.BLACK));
        map.put(new Location(Column.H, Row.EIGHT), new Rook(Color.WHITE));
    }

    private void initialKnightSetting() {
        map.put(new Location(Column.B, Row.ONE), new Knight(Color.BLACK));
        map.put(new Location(Column.B, Row.EIGHT), new Knight(Color.WHITE));
        map.put(new Location(Column.G, Row.ONE), new Knight(Color.BLACK));
        map.put(new Location(Column.G, Row.EIGHT), new Knight(Color.WHITE));
    }

    private void initialBishopSetting() {
        map.put(new Location(Column.C, Row.ONE), new Bishop(Color.BLACK));
        map.put(new Location(Column.C, Row.EIGHT), new Bishop(Color.WHITE));
        map.put(new Location(Column.F, Row.ONE), new Bishop(Color.BLACK));
        map.put(new Location(Column.F, Row.EIGHT), new Bishop(Color.WHITE));
    }

    private void initialQueenSetting() {
        map.put(new Location(Column.D, Row.ONE), new Queen(Color.BLACK));
        map.put(new Location(Column.D, Row.EIGHT), new Queen(Color.WHITE));
    }

    private void initialKingSetting() {
        map.put(new Location(Column.E, Row.ONE), new King(Color.BLACK));
        map.put(new Location(Column.E, Row.EIGHT), new King(Color.WHITE));
    }
}

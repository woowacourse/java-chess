package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board startGame() {
        Map<Position, Piece> map = new HashMap<>();
        initializePawn(map);
        initializeKnight(map);
        initializeBishop(map);
        initializeRook(map);
        initializeQueen(map);
        initializeKing(map);

        return new Board(map);
    }

    private static void initializePawn(Map<Position, Piece> map) {
        for (Column column : Column.values()) {
            map.put(new Position(Row.TWO, column), new Pawn(Team.WHITE));
        }
        for (Column column : Column.values()) {
            map.put(new Position(Row.SEVEN, column), new Pawn(Team.BLACK));
        }
    }

    private static void initializeKnight(Map<Position, Piece> map) {
        map.put(new Position(Row.ONE, Column.B), new Knight(Team.WHITE));
        map.put(new Position(Row.ONE, Column.G), new Knight(Team.WHITE));
        map.put(new Position(Row.EIGHT, Column.B), new Knight(Team.BLACK));
        map.put(new Position(Row.EIGHT, Column.G), new Knight(Team.BLACK));
    }

    private static void initializeBishop(Map<Position, Piece> map) {
        map.put(new Position(Row.ONE, Column.C), new Bishop(Team.WHITE));
        map.put(new Position(Row.ONE, Column.F), new Bishop(Team.WHITE));
        map.put(new Position(Row.EIGHT, Column.C), new Bishop(Team.BLACK));
        map.put(new Position(Row.EIGHT, Column.F), new Bishop(Team.BLACK));
    }

    private static void initializeRook(Map<Position, Piece> map) {
        map.put(new Position(Row.ONE, Column.A), new Rook(Team.WHITE));
        map.put(new Position(Row.ONE, Column.H), new Rook(Team.WHITE));
        map.put(new Position(Row.EIGHT, Column.A), new Rook(Team.BLACK));
        map.put(new Position(Row.EIGHT, Column.H), new Rook(Team.BLACK));
    }

    private static void initializeQueen(Map<Position, Piece> map) {
        map.put(new Position(Row.ONE, Column.D), new Queen(Team.WHITE));
        map.put(new Position(Row.EIGHT, Column.D), new Queen(Team.BLACK));
    }

    private static void initializeKing(Map<Position, Piece> map) {
        map.put(new Position(Row.ONE, Column.E), new King(Team.WHITE));
        map.put(new Position(Row.EIGHT, Column.E), new King(Team.BLACK));
    }
}

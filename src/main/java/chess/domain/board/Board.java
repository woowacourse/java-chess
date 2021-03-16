package chess.domain.board;


import chess.domain.piece.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {
    private final Map<Location, Piece> board;

    public Board() {
        this.board = createBoard();
    }

    private Map<Location, Piece> createBoard() {
        Map<Location, Piece> board = initialize();

        board.put(Location.of("a","8"), new Rook(Team.BLACK));
        board.put(Location.of("b","8"), new Knight(Team.BLACK));
        board.put(Location.of("c","8"), new Bishop(Team.BLACK));
        board.put(Location.of("d","8"), new Queen(Team.BLACK));
        board.put(Location.of("e","8"), new King(Team.BLACK));
        board.put(Location.of("f","8"), new Bishop(Team.BLACK));
        board.put(Location.of("g","8"), new Knight(Team.BLACK));
        board.put(Location.of("h","8"), new Rook(Team.BLACK));
        board.put(Location.of("a","7"), new Pawn(Team.BLACK));
        board.put(Location.of("b","7"), new Pawn(Team.BLACK));
        board.put(Location.of("c","7"), new Pawn(Team.BLACK));
        board.put(Location.of("d","7"), new Pawn(Team.BLACK));
        board.put(Location.of("e","7"), new Pawn(Team.BLACK));
        board.put(Location.of("f","7"), new Pawn(Team.BLACK));
        board.put(Location.of("g","7"), new Pawn(Team.BLACK));
        board.put(Location.of("h","7"), new Pawn(Team.BLACK));

        board.put(Location.of("a","1"), new Rook(Team.WHITE));
        board.put(Location.of("b","1"), new Knight(Team.WHITE));
        board.put(Location.of("c","1"), new Bishop(Team.WHITE));
        board.put(Location.of("d","1"), new Queen(Team.WHITE));
        board.put(Location.of("e","1"), new King(Team.WHITE));
        board.put(Location.of("f","1"), new Bishop(Team.WHITE));
        board.put(Location.of("g","1"), new Knight(Team.WHITE));
        board.put(Location.of("h","1"), new Rook(Team.WHITE));
        board.put(Location.of("a","2"), new Pawn(Team.WHITE));
        board.put(Location.of("b","2"), new Pawn(Team.WHITE));
        board.put(Location.of("c","2"), new Pawn(Team.WHITE));
        board.put(Location.of("d","2"), new Pawn(Team.WHITE));
        board.put(Location.of("e","2"), new Pawn(Team.WHITE));
        board.put(Location.of("f","2"), new Pawn(Team.WHITE));
        board.put(Location.of("g","2"), new Pawn(Team.WHITE));
        board.put(Location.of("h","2"), new Pawn(Team.WHITE));

        return board;
    }

    public Piece findPieceFromLocation(Location location) {
        return board.get(location);
    }

    public Map<Location, Piece> initialize() {
        Map<Location, Piece> board = new LinkedHashMap<>();

        for (Location location : Location.getLocations()) {
            board.put(location, null);
        }

        return board;
    }

    public Map<Location, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}

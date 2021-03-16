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

        board.put(Location.of(Horizontal.A, Vertical.EIGHT), new Rook(Team.BLACK));
        board.put(Location.of(Horizontal.B, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Location.of(Horizontal.C, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Location.of(Horizontal.D, Vertical.EIGHT), new Queen(Team.BLACK));
        board.put(Location.of(Horizontal.E, Vertical.EIGHT), new King(Team.BLACK));
        board.put(Location.of(Horizontal.F, Vertical.EIGHT), new Bishop(Team.BLACK));
        board.put(Location.of(Horizontal.G, Vertical.EIGHT), new Knight(Team.BLACK));
        board.put(Location.of(Horizontal.H, Vertical.EIGHT), new Rook(Team.BLACK));
        setPawn(board, Vertical.SEVEN, Team.BLACK);

        board.put(Location.of(Horizontal.A, Vertical.ONE), new Rook(Team.WHITE));
        board.put(Location.of(Horizontal.B, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Location.of(Horizontal.C, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Location.of(Horizontal.D, Vertical.ONE), new Queen(Team.WHITE));
        board.put(Location.of(Horizontal.E, Vertical.ONE), new King(Team.WHITE));
        board.put(Location.of(Horizontal.F, Vertical.ONE), new Bishop(Team.WHITE));
        board.put(Location.of(Horizontal.G, Vertical.ONE), new Knight(Team.WHITE));
        board.put(Location.of(Horizontal.H, Vertical.ONE), new Rook(Team.WHITE));
        setPawn(board, Vertical.TWO, Team.WHITE);

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

    private void setPawn(Map<Location, Piece> board, Vertical vertical, Team team) {
        for (Horizontal horizontal : Horizontal.values()) {
            board.put(Location.of(horizontal, vertical), new Pawn(team));
        }
    }
}

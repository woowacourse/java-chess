package chess.domain;

import chess.domain.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Position, Piece> boardState;

    public Board() {
        boardState = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Position position = new Position(i, j);
                boardState.put(position, new Blank(position, Team.BLANK));
            }
        }
    }

    public void initialize() {
        initializeRook();
        initializeKnight();
        initializeBishop();
        initializeQueen();
        initializeKing();
        initializePawn();
    }

    private void initializeRook() {
        Position position;
        position = new Position(1, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = new Position(8, 1);
        boardState.put(position, new Rook(position, Team.WHITE));
        position = new Position(1, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
        position = new Position(8, 8);
        boardState.put(position, new Rook(position, Team.BLACK));
    }

    private void initializeKnight() {
        Position position;
        position = new Position(2, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = new Position(7, 1);
        boardState.put(position, new Knight(position, Team.WHITE));
        position = new Position(2, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
        position = new Position(7, 8);
        boardState.put(position, new Knight(position, Team.BLACK));
    }

    private void initializeBishop() {
        Position position;
        position = new Position(3, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = new Position(6, 1);
        boardState.put(position, new Bishop(position, Team.WHITE));
        position = new Position(3, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
        position = new Position(6, 8);
        boardState.put(position, new Bishop(position, Team.BLACK));
    }

    private void initializeQueen() {
        Position position;
        position = new Position(4, 1);
        boardState.put(position, new Queen(position, Team.WHITE));
        position = new Position(4, 8);
        boardState.put(position, new Queen(position, Team.BLACK));
    }

    private void initializeKing() {
        Position position;
        position = new Position(5, 1);
        boardState.put(position, new King(position, Team.WHITE));
        position = new Position(5, 8);
        boardState.put(position, new King(position, Team.BLACK));
    }

    private void initializePawn() {
        Position position;
        for (int i = 1; i <= 8; i++) {
            position = new Position(i, 2);
            boardState.put(position, new Pawn(position, Team.WHITE));
            position = new Position(i, 7);
            boardState.put(position, new Pawn(position, Team.BLACK));
        }
    }
}

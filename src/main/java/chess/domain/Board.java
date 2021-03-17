package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.*;

public class Board {

    private final Map<Position, Piece> squares;

    public Board() {
        squares = initSquares();
    }

    private Map<Position, Piece> initSquares() {
        Map<Position, Piece> squares = new LinkedHashMap<>();

        squares.put(Position.valueOf("a8"), new Rook(Team.BLACK));
        squares.put(Position.valueOf("b8"), new Knight(Team.BLACK));
        squares.put(Position.valueOf("c8"), new Bishop(Team.BLACK));
        squares.put(Position.valueOf("d8"), new Queen(Team.BLACK));
        squares.put(Position.valueOf("e8"), new King(Team.BLACK));
        squares.put(Position.valueOf("f8"), new Bishop(Team.BLACK));
        squares.put(Position.valueOf("g8"), new Knight(Team.BLACK));
        squares.put(Position.valueOf("h8"), new Rook(Team.BLACK));

        squares.put(Position.valueOf("a7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("b7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("c7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("d7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("e7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("f7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("g7"), new Pawn(Team.BLACK));
        squares.put(Position.valueOf("h7"), new Pawn(Team.BLACK));

        squares.put(Position.valueOf("a6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("b6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("c6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("d6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("e6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("f6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("g6"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("h6"), new Empty(Team.EMPTY));

        squares.put(Position.valueOf("a5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("b5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("c5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("d5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("e5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("f5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("g5"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("h5"), new Empty(Team.EMPTY));

        squares.put(Position.valueOf("a4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("b4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("c4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("d4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("e4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("f4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("g4"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("h4"), new Empty(Team.EMPTY));

        squares.put(Position.valueOf("a3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("b3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("c3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("d3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("e3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("f3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("g3"), new Empty(Team.EMPTY));
        squares.put(Position.valueOf("h3"), new Empty(Team.EMPTY));

        squares.put(Position.valueOf("a2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("b2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("c2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("d2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("e2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("f2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("g2"), new Pawn(Team.WHITE));
        squares.put(Position.valueOf("h2"), new Pawn(Team.WHITE));

        squares.put(Position.valueOf("a1"), new Rook(Team.WHITE));
        squares.put(Position.valueOf("b1"), new Knight(Team.WHITE));
        squares.put(Position.valueOf("c1"), new Bishop(Team.WHITE));
        squares.put(Position.valueOf("d1"), new Queen(Team.WHITE));
        squares.put(Position.valueOf("e1"), new King(Team.WHITE));
        squares.put(Position.valueOf("f1"), new Bishop(Team.WHITE));
        squares.put(Position.valueOf("g1"), new Knight(Team.WHITE));
        squares.put(Position.valueOf("h1"), new Rook(Team.WHITE));

        return squares;
    }

    public Map<Position, Piece> getSquares() {
        return squares;
    }
}

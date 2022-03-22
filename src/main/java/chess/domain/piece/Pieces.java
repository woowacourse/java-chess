package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionX;
import chess.domain.position.PositionY;

import java.util.HashMap;
import java.util.Map;

public final class Pieces {
    private final Map<Piece, Position> pieces;

    public Pieces() {
        this.pieces = new HashMap<>();
        initialize();
    }

    private void initialize() {
        // white pawns
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.A, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.B, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.C, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.D, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.E, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.F, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.G, PositionY.RANK_2));
        pieces.put(new Pawn(Color.WHITE), new Position(PositionX.H, PositionY.RANK_2));

        // white large pieces
        pieces.put(new Rook(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Knight(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Bishop(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Queen(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new King(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Bishop(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Knight(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));
        pieces.put(new Rook(Color.WHITE), new Position(PositionX.A, PositionY.RANK_1));

        // black pawns
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.A, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.B, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.C, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.D, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.E, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.F, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.G, PositionY.RANK_7));
        pieces.put(new Pawn(Color.BLACK), new Position(PositionX.H, PositionY.RANK_7));

        // black large pieces
        pieces.put(new Rook(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Knight(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Bishop(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Queen(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new King(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Bishop(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Knight(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
        pieces.put(new Rook(Color.BLACK), new Position(PositionX.A, PositionY.RANK_8));
    }
}

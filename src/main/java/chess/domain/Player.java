package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }


    public Piece pickStartPiece(Position position, Pieces pieces) {
        Piece piece = pieces.getPieceOf(position);
        if (piece.isSameColor(color)) {
            return piece;
        }
        throw new IllegalArgumentException();
    }

    public void move(Position from, Position to, Pieces pieces) {
        Piece start = pickStartPiece(from, pieces);
        Piece piece = pieces.getPieceOf(to);
        if (piece.isEmpty()) {
            start.moveToEmpty(to, pieces);
            return;
        }
        if (piece.isSameColor(color)) {
            throw new IllegalArgumentException();
        }
        start.moveForKill(to, pieces);
        pieces.delete(piece);
    }
}

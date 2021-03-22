package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Pieces pieces;

    public Board(Pieces pieces) {
        this.pieces = pieces;
    }

    public void move(Color color, Position from, Position to) {
        Piece start = pickStartPiece(color, from);
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

    public Piece pickStartPiece(Color color, Position position) {
        Piece piece = pieces.getPieceOf(position);
        if (piece.isSameColor(color)) {
            return piece;
        }
        throw new IllegalArgumentException();
    }

    public boolean isNotEnd() {
        return pieces.toList()
                     .stream()
                     .filter(Piece::isKing)
                     .count() == 2;
    }

    public double score(Color color) {
        return pieces.score(color);
    }

    public Map<Position, Piece> allPieces() {
        Map<Position, Piece> pieceMap = new HashMap<>();
        for (Position position : Position.all()) {
            Piece piece = new Empty();
            if (pieces.hasPieceOf(position)) {
                piece = pieces.getPieceOf(position);
            }
            pieceMap.put(position, piece);
        }
        return pieceMap;
    }
}

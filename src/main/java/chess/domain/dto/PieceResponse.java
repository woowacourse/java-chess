package chess.domain.dto;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.square.Square;

public record PieceResponse(Square square, Piece piece) {

    public int getFileIndex() {
        return square.getFileIndex();
    }

    public int getRankIndex() {
        return square.getRankIndex();
    }

    public Type getType() {
        return piece.type();
    }

    public Color getColor() {
        return piece.color();
    }
}

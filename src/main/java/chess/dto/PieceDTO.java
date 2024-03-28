package chess.dto;

import chess.model.piece.Piece;
import chess.model.piece.Type;
import chess.model.position.Position;

public record PieceDTO(int file, int rank, String type) {
    public static PieceDTO from(Position position, Piece piece) {
        Type type = Type.from(piece);
        return new PieceDTO(position.getFile(), position.getRank(), type.name());
    }
}

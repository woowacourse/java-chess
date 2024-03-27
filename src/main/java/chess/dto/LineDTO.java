package chess.dto;

import chess.model.piece.Piece;
import java.util.List;

public record LineDTO(List<String> line) {
    public static LineDTO from(List<Piece> pieces) {
        return new LineDTO(pieces.stream()
                .map(Piece::getName)
                .toList());
    }
}

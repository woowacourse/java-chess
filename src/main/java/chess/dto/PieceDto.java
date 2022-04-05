package chess.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private final String emoji;

    public PieceDto(Piece piece) {
        this.emoji = piece.getEmoji();
    }

    public String getEmoji() {
        return emoji;
    }
}

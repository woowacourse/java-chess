package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.PlayingCamp;

public class PieceDto {
    private final PlayingCamp playingCamp;
    private final PieceType pieceType;

    public PieceDto(Piece piece) {
        this.playingCamp = piece.getColor();
        this.pieceType = piece.getPieceType();
    }

    public PlayingCamp getColor() {
        return playingCamp;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}

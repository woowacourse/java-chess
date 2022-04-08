package chess.dto;

import chess.domain.Camp;
import chess.domain.piece.Piece;

public class PieceDto {
    private final String type;
    private final String camp;

    public PieceDto(String type, String camp) {
        this.type = type;
        this.camp = camp;
    }

    public static PieceDto of(String type, boolean isWhite) {
        if (isWhite) {
            return new PieceDto(type, "white");
        }
        return new PieceDto(type, "black");
    }

    public static PieceDto of(Piece piece) {
        return of(piece.getType().toString(), piece.isCamp(Camp.WHITE));
    }

    public String getType() {
        return type;
    }

    public String getCamp() {
        return camp;
    }
}

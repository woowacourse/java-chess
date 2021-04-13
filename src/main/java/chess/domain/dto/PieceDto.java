package chess.domain.dto;

import chess.domain.piece.Piece;

public class PieceDto {

    private final char signature;
    private final String team;
    private final String location;

    public PieceDto(char signature, String team, String location) {
        this.signature = signature;
        this.team = team;
        this.location = location;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(
            piece.getSignature(),
            piece.getTeam().getValue(),
            String.valueOf(piece.getX()) + String.valueOf(piece.getY()));
    }

    public char getSignature() {
        return signature;
    }

    public String getTeam() {
        return team;
    }

    public String getLocation() {
        return location;
    }
}

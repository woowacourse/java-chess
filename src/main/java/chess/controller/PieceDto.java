package chess.controller;

import chess.domain.Piece;

public class PieceDto {
    private final String team;
    private final String type;

    public PieceDto(final String team, final String type) {
        this.team = team;
        this.type = type;
    }

    public static PieceDto from(final Piece piece) {
        piece.getClass();
        piece.getTeam();
    }
}

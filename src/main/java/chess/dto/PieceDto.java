package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Role;
import chess.domain.square.Color;

public class PieceDto {
    private final Color color;
    private final Role role;

    private PieceDto(final Color color, final Role role) {
        this.color = color;
        this.role = role;
    }

    public static PieceDto from(final Piece piece) {
        Color color = piece.getTeam().getColor();
        Role role = piece.getRole();
        return new PieceDto(color, role);
    }

    public Color getColor() {
        return color;
    }

    public Role getRole() {
        return role;
    }
}

package database.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class SquareDto {

    private final int x_pos;
    private final int y_pos;
    private final String role;
    private final String team;

    private SquareDto(int x_pos, int y_pos, String role, String team) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.role = role;
        this.team = team;
    }

    public static SquareDto of(Position position, Piece piece) {
        return new SquareDto(position.getX(), position.getY(), piece.getRole().name(), piece.getTeam().name());
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }
}

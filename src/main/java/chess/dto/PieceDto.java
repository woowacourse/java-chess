package chess.dto;

import java.util.Objects;

public class PieceDto {
    private final int x;
    private final int y;
    private final String name;
    private final boolean team;

    public PieceDto(int x, int y, String name, boolean team) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.team = team;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public boolean isTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return x == pieceDto.x &&
                y == pieceDto.y &&
                team == pieceDto.team &&
                Objects.equals(name, pieceDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name, team);
    }
}

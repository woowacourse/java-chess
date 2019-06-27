package chess.dto;

import java.util.Objects;

public class BoardDto {
    private final String piece;
    private final String team;
    private final String point;
    private final int round;

    public BoardDto(String piece, String team, String point, int round) {
        this.piece = piece;
        this.team = team;
        this.point = point;
        this.round = round;
    }

    public String getPiece() {
        return piece;
    }

    public String getTeam() {
        return team;
    }

    public String getPoint() {
        return point;
    }

    public int getRound() {
        return round;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return round == boardDto.round &&
                Objects.equals(piece, boardDto.piece) &&
                Objects.equals(team, boardDto.team) &&
                Objects.equals(point, boardDto.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, team, point, round);
    }
}

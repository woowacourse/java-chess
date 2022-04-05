package chess.dto;

import java.util.Locale;

public class PieceDTO {

    private final String name;
    private final char column;
    private final int row;
    private final String team;

    public PieceDTO(String name, char column, int row, String team) {
        this.name = name;
        this.column = column;
        this.row = row;
        this.team = team.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public String getTeam() {
        return team;
    }
}

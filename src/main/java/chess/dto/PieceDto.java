package chess.dto;

public class PieceDto {
    private final String file;
    private final String rank;
    private final String type;
    private final String team;

    public PieceDto(String file, String rank, String type, String team) {
        this.file = file;
        this.rank = rank;
        this.type = type;
        this.team = team;
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }

    public String getType() {
        return type;
    }

    public String getTeam() {
        return team;
    }
}

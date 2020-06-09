package chess.domain.dto;

public class PieceDto {
    private String id;
    private String team;
    private String name;
    private String position;

    public PieceDto(String id, String team, String name, String position) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.position = position;
    }

    public PieceDto(String position, String name) {
        this(null, null, position, name);
    }

    public String getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}

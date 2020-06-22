package chess.domain.dto;

public class PieceDto {
    private String position;
    private String name;

    public PieceDto(String position, String name) {
        this.position = position;
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}

package chess.dto;

public class PieceDto {

    private final String position;
    private final String name;

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

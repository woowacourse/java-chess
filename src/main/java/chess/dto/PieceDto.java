package chess.dto;

public class PieceDto {

    private final String name;
    private final String position;

    public PieceDto(final String name, final String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}

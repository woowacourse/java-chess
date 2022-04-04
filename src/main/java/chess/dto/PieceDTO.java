package chess.dto;

public class PieceDTO {

    private final String position;
    private final String name;

    public PieceDTO(String position, String name) {
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

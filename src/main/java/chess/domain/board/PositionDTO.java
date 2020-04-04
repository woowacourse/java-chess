package chess.domain.board;

public class PositionDTO {

    private final String position;

    private PositionDTO(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public static PositionDTO create(Position position) {
        String name = position.getName();
        return new PositionDTO(name);
    }
}

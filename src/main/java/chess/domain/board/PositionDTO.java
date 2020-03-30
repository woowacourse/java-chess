package chess.domain.board;

public class PositionDTO {

    private static final String DELIMITER = "";

    private final String position;

    public PositionDTO(Position position) {
        this.position = String.join(DELIMITER, position.getColumn().getName(), position.getRow().getName());
    }

    public String getPosition() {
        return position;
    }
}

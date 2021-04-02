package chess.domain.dto;

public class PositionDto {
    private final String position;

    public PositionDto(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return position;
    }
}

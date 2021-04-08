package chess.dto;

public final class PointsDTO {
    private final String start_point;
    private final String end_point;

    public PointsDTO(final String start_point, final String end_point) {
        this.start_point = start_point;
        this.end_point = end_point;
    }

    public String getStartPoint() {
        return start_point;
    }

    public String getEndPoint() {
        return end_point;
    }
}

package chess.dto;

public final class PointsDTO {
    private final String startPoint;
    private final String endPoint;

    public PointsDTO(final String startPoint, final String endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}

package chess.dto;

public class RequestDto {
    private String sourcePoint;
    private String targetPoint;

    public String getSourcePoint() {
        return sourcePoint;
    }

    public void setSourcePoint(String sourcePoint) {
        this.sourcePoint = sourcePoint;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }
}

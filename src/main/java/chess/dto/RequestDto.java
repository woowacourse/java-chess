package chess.dto;

public class RequestDto {
    private final String source;
    private final String target;

    public RequestDto(String sourcePoint, String targetPoint) {
        this.source = sourcePoint;
        this.target = targetPoint;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}

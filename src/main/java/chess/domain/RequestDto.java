package chess.domain;

public class RequestDto {
    private String source;
    private String target;

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

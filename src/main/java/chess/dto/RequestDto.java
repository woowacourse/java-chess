package chess.dto;

public class RequestDto {
    private final String source;
    private final String target;

    public RequestDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}

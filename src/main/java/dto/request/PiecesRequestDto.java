package dto.request;

public class PiecesRequestDto {

    private final String source;
    private final String target;

    public PiecesRequestDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }

}

package chess.dto;

public class MoveDto {

    private String source;
    private String target;

    public MoveDto(final String source, final String target) {
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

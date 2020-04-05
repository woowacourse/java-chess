package chess.dto;

public class MoveSquareDto {

    private final String source;
    private final String target;

    public MoveSquareDto(String source, String target) {
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

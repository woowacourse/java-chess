package chess.dto;

public class MoveSquareDTO {

    private final String source;
    private final String target;

    public MoveSquareDTO(String source, String target) {
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

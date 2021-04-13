package chess.domain.dto.move;

public class MoveRequestDto {

    private final String source;
    private final String target;

    public MoveRequestDto(String source, String target) {
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

package chess.dto;

public class MoveRequestDto {

    private final String id;
    private final String source;
    private final String target;

    public MoveRequestDto(final String id, final String source, final String target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }

    public String getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}

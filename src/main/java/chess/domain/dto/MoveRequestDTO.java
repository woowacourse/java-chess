package chess.domain.dto;

public class MoveRequestDTO {

    private String source;
    private String target;

    public MoveRequestDTO(String source, String target) {
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

package chess.service.dto;

public class MoveRequestDto {
    private String source;
    private String target;

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

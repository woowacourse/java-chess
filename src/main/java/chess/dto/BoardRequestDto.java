package chess.dto;

public class BoardRequestDto {

    private final String source;
    private final String target;

    public BoardRequestDto(String source, String target) {
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

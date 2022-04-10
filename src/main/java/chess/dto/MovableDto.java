package chess.dto;

public class MovableDto {

    private String source;
    private String target;

    public MovableDto(String source, String target) {
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

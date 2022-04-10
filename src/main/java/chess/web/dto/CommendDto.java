package chess.web.dto;

public class CommendDto {

    private String source;
    private String target;

    public CommendDto(String source, String target) {
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

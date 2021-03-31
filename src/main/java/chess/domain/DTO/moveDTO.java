package chess.domain.DTO;

public class moveDTO {

    private String source;
    private String target;

    public moveDTO(String source, String target) {
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

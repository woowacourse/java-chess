package chess.view.dto.game;

public class MoveRequest {

    private final String source;
    private final String target;

    public MoveRequest(String source, String target) {
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

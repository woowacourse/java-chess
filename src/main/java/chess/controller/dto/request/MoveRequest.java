package chess.controller.dto.request;

public class MoveRequest {

    private final String start;
    private final String target;

    public MoveRequest(String start, String target) {
        this.start = start;
        this.target = target;
    }

    public String getStart() {
        return start;
    }

    public String getTarget() {
        return target;
    }
}

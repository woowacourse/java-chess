package chess.domain.dto;

public class MoveStatusDto {
    private final String source;
    private final String target;
    private final String turn;

    public MoveStatusDto(String source, String target, String turn) {
        this.source = source;
        this.target = target;
        this.turn = turn;
    }

    public String getTarget() {
        return target;
    }

    public String getTurn() {
        return turn;
    }

    public String getSource() {
        return source;
    }
}

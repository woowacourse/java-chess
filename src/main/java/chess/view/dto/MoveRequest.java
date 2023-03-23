package chess.view.dto;

public class MoveRequest {

    PositionRequest source;
    PositionRequest target;

    private MoveRequest(PositionRequest source, PositionRequest target) {
        this.source = source;
        this.target = target;
    }

    public static MoveRequest of(String source, String target) {
        return new MoveRequest(createPosition(source), createPosition(target));
    }

    private static PositionRequest createPosition(String input) {
        return new PositionRequest(getFileFrom(input), getRankFrom(input));
    }

    private static String getFileFrom(String input) {
        return Character.toString(input.charAt(0));
    }

    private static String getRankFrom(String input) {
        return Character.toString(input.charAt(1));
    }

    public PositionRequest getSource() {
        return source;
    }

    public PositionRequest getTarget() {
        return target;
    }
}

package chess.dto;

import java.util.List;

public class MoveDto {

    private static final String COMMAND = "move";

    private final String source;
    private final String target;

    public MoveDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public List<String> getCommand() {
        return List.of(COMMAND, source, target);
    }
}

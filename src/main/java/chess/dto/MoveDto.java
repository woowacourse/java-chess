package chess.dto;

import java.util.List;

public class MoveDto {

    private static final String MOVE_COMMAND = "move";

    private final String source;
    private final String target;

    public MoveDto(String source, String target) {
        this.source = source;
        this.target = target;
    }

    public List<String> getOptions() {
        return List.of(MOVE_COMMAND, source, target);
    }
}

package chess.domain.event;

import chess.domain.board.position.Position;

public class MoveCommand {

    private static final String DESCRIPTION_DELIMITER = " ";

    private final Position source;
    private final Position target;

    public MoveCommand(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public MoveCommand(String description) {
        String[] positions = description.split(DESCRIPTION_DELIMITER);
        this.source = Position.of(positions[0]);
        this.target = Position.of(positions[1]);
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public String toDescription() {
        String sourceKey = source.toKey();
        String targetKey = target.toKey();

        return sourceKey + DESCRIPTION_DELIMITER + targetKey;
    }
}

package chess.dto.request;

import chess.domain.board.position.Position;

public class MoveCommandDto {

    private static final String DESCRIPTION_DELIMITER = " ";

    private final Position source;
    private final Position target;

    public MoveCommandDto(String source, String target) {
        this.source = Position.of(source);
        this.target = Position.of(target);
    }

    public MoveCommandDto(String description) {
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

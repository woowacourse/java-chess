package chess.dto;

import chess.domain.Color;
import chess.domain.Position;

public class MovementDto {
    private final Position source;
    private final Position target;
    private final Color player;

    private MovementDto(final Position source, final Position target, final Color player) {
        this.source = source;
        this.target = target;
        this.player = player;
    }

    public static MovementDto create(final Position source, final Position target, final Color player) {
        return new MovementDto(source, target, player);
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }

    public Color getPlayer() {
        return player;
    }

    public String getSourceValue() {
        return source.toString();
    }

    public String getTargetValue() {
        return target.toString();
    }
}

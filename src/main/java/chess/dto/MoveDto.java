package chess.dto;

import chess.domain.square.Square;

public class MoveDto {
    private final String source;
    private final String target;

    public MoveDto(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public static MoveDto of(final Square source, final Square target) {
        return new MoveDto(source.toString(), target.toString());
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}

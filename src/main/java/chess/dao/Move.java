package chess.dao;

import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;

public class Move {

    private final String source;
    private final String target;

    public Move(final String source, final String target) {
        this.source = source;
        this.target = target;
    }

    public Move(final Position source, final Position target) {
        this(source.getPosition(), target.getPosition());
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public Position toSourcePosition() {
        return toPosition(source);
    }

    public Position toTargetPosition() {
        return toPosition(target);
    }

    private Position toPosition(final String move) {
        final File file = File.valueOf(move.substring(0, 1));

        final int rankValue = Integer.parseInt(move.substring(1, 2));
        final Rank rank = Rank.findRank(rankValue);

        return Position.of(file, rank);
    }
}

package chess.domain.position.movestrategy;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class South implements MoveStrategy {

    @Override
    public Position move(final File file, final Rank rank) {
        return new Position(file, rank.minus());
    }
}

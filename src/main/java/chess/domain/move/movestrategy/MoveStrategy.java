package chess.domain.move.movestrategy;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.File;

public interface MoveStrategy {

    Position move(final File file, final Rank rank);
}

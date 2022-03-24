package chess.domain.position.diraction;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.File;

public interface Direction {

    Position move(final File file, final Rank rank);
}

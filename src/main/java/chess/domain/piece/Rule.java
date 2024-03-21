package chess.domain.piece;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

@FunctionalInterface
public interface Rule {
    boolean obey(FileDifference fileDifference, RankDifference rankDifference);
}

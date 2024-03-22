package chess.domain.piece.rule;

import chess.domain.position.FileDifference;
import chess.domain.position.RankDifference;

@FunctionalInterface
public interface Rule {
    boolean obey(FileDifference fileDifference, RankDifference rankDifference);
}

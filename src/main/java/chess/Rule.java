package chess;

import chess.position.FileDifference;
import chess.position.RankDifference;

@FunctionalInterface
public interface Rule {
    boolean obey(FileDifference fileDifference, RankDifference rankDifference);
}

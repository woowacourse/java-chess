package chess.domain.direction.core;

import chess.domain.piece.core.Piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum TargetStatus {
    EMPTY((source, target) -> target == null),
    ENEMY((source, target) -> !source.isSameTeam(target));

    BiFunction<Piece, Piece, Boolean> checkStatus;

    TargetStatus(BiFunction<Piece, Piece, Boolean> checkStatus) {
        this.checkStatus = checkStatus;
    }

    public static TargetStatus valuesOf(Piece source, Piece target) {
        return Arrays.stream(values())
                .filter(e -> e.checkStatus(source, target))
                .findAny()
                .orElse(null);
    }

    public boolean checkStatus(Piece source, Piece target) {
        return checkStatus.apply(source, target);
    }
}

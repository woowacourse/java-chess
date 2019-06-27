package chess.domain.direction.core;

import chess.domain.piece.core.Piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum TargetStatus {
    EMPTY((source, target) -> target == null),
    ENEMY((source, target) -> !source.isTeam(target));

    BiFunction<Piece, Piece, Boolean> checkStatus;

    TargetStatus(BiFunction<Piece, Piece, Boolean> checkStatus) {
        this.checkStatus = checkStatus;
    }

    public static TargetStatus valuesOf(Piece source, Piece target) {
        try {
            return Arrays.stream(values())
                    .filter(e -> e.checkStatus(source, target))
                    .findAny()
                    .orElse(null);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("TargetStatus 클래스에 있는 valuesOf 함수에서 NullPointException 발생");
        }
    }

    public boolean checkStatus(Piece source, Piece target) {
        return checkStatus.apply(source, target);
    }
}

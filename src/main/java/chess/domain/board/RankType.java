package chess.domain.board;

import java.util.Arrays;
import java.util.List;

import static chess.domain.board.RankCoordinate.*;

public enum RankType {
    SIDE_RANK(List.of(ONE, EIGHT)),
    PAWN_RANK(List.of(TWO, SEVEN)),
    EMPTY_RANK(List.of(THREE, FOUR, FIVE, SIX)),
    ;

    private final List<RankCoordinate> rankCoordinates;

    RankType(List<RankCoordinate> rankCoordinates) {
        this.rankCoordinates = rankCoordinates;
    }

    public static RankType of(RankCoordinate rankCoordinate) {
        return Arrays.stream(values())
                .filter(rankType -> rankType.rankCoordinates.contains(rankCoordinate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 행 번호를 입력해주세요."));
    }
}

package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;

public enum PawnMoveInfo {
    LOWER(Team.WHITE, Row.TWO, Row.FOUR, -1),
    UPPER(Team.BLACK, Row.SEVEN, Row.FIVE, 1);

    private final Team team;
    private final Row initial;
    private final Row jumped;
    private final int rowGap;

    PawnMoveInfo(Team team, Row initial, Row jumped, int rowGap) {
        this.team = team;
        this.initial = initial;
        this.jumped = jumped;
        this.rowGap = rowGap;
    }

    public static PawnMoveInfo of(Team team) {
        return Arrays.stream(values())
                .filter(value -> value.team.equals(team))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("정의 되지 않은 폰의 팀입니다."));
    }

    public boolean isInitialRow(Position position) {
        return position.isRowEquals(initial);
    }

    public Position getJumpedPositionOf(Position position) {
        return Position.of(position.getColumn(), jumped);
    }

    public boolean isValidRowGap(int rowGap) {
        return this.rowGap == rowGap;
    }
}
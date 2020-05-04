package chess.domain.position;

import chess.domain.piece.team.Team;

import java.util.Arrays;

public enum PawnInitialRow {
    WHITE(Team.WHITE, 2),
    BLACK(Team.BLACK, 7);

    private final Team team;
    private final int value;

    PawnInitialRow(Team team, int value) {
        this.team = team;
        this.value = value;
    }

    public static PawnInitialRow valueOf(Team team) {
        return Arrays.stream(values())
                .filter(pawnInitialRow -> pawnInitialRow.team == team)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 팀에 맞는 행이 없습니다."));
    }

    public boolean match(Position position) {
        return value == position.getY();
    }
}

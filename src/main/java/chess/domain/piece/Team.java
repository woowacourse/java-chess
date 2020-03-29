package chess.domain.piece;

import chess.domain.util.Direction;

import java.util.Arrays;

public enum Team {
    WHITE(Direction.NORTH, 2, 1),
    BLACK(Direction.SOUTH, 7, 0),
    BLANK(Direction.NONE, 0, -1);

    private static final String TEAM_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 팀을 찾을 수 없습니다.";

    private final Direction pawnForwardDirection;
    private final int initialPawnRow;
    private final int turnFlag;

    Team(Direction pawnForwardDirection, int initialPawnRow, int turnFlag) {
        this.pawnForwardDirection = pawnForwardDirection;
        this.initialPawnRow = initialPawnRow;
        this.turnFlag = turnFlag;
    }

    public boolean isNotSame(Team team) {
        return this != team && team != BLANK;
    }

    public Direction getForwardDirection() {
        return pawnForwardDirection;
    }

    public int getInitialPawnRow() {
        return initialPawnRow;
    }

    public static boolean isSameTeam(int turnFlag, Piece fromPiece) {
        return Team.of(turnFlag) == fromPiece.team;
    }

    private static Team of(int turnFlag) {
        return Arrays.stream(values())
                .filter(team -> team.turnFlag == turnFlag)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(TEAM_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}

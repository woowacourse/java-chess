package chess.domain.piece;

import chess.domain.board.RankCoordinate;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static Team of(RankCoordinate rankCoordinate) {
        if (rankCoordinate.isWhiteRank()) {
            return Team.WHITE;
        }
        if (rankCoordinate.isBlackRank()) {
            return Team.BLACK;
        }
        return Team.EMPTY;
    }

    public boolean isOpposite(Team team) {
        return this.getReverseTeam() == team;
    }

    public Team getReverseTeam() {
        if (this == BLACK) {
            return WHITE;
        }
        if (this == WHITE) {
            return BLACK;
        }
        return EMPTY;
    }

    public int getDirection() {
        if (this == BLACK) {
            return Direction.DOWN.getDirection();
        }
        return Direction.UP.getDirection();
    }
}

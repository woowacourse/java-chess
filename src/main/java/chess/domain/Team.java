package chess.domain;

import chess.domain.position.Row;

public enum Team {
    BLACK(Row.getLocation("7")),
    WHITE(Row.getLocation("2"));

    private final int pawnInitRow;

    Team(final int pawnInitRow) {
        this.pawnInitRow = pawnInitRow;
    }

    public static Team enemyTeam(final Team userTeam) {
        if (Team.BLACK.equals(userTeam)) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isInitPawn(final int row) {
        return pawnInitRow == row;
    }
}

package chess.domain;

import chess.domain.util.RowConverter;

public enum Team {
    BLACK(RowConverter.getLocation("7")),
    WHITE(RowConverter.getLocation("2"));

    private final int pawnInitRow;

    Team(final int pawnInitRow) {
        this.pawnInitRow = pawnInitRow;
    }

    public static Team getAnotherTeam(final Team userTeam) {
        if (Team.BLACK.equals(userTeam)) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isInitPawn(final int row) {
        return pawnInitRow == row;
    }
}

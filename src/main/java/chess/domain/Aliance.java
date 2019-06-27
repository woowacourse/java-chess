package chess.domain;

public enum Aliance {
    BLACK(1), WHITE(2);

    private int teamId;

    Aliance(int teamId) {
        this.teamId = teamId;
    }

    public static Aliance valueOf(int teamId) {
        if (teamId == BLACK.teamId) {
            return Aliance.BLACK;
        }
        return Aliance.WHITE;
    }

    public int getTeamId() {
        return teamId;
    }
}

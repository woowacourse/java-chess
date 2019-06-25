package chess.domain;

public enum Team {
    BLACK, WHITE;

    public static MoveRule pawnMoveRule(Team team) {
        if (team == BLACK) {
            return MoveRules::blackPawn;
        }
        return MoveRules::whitePawn;
    }

    public Team enemy() {
        return this == BLACK ? WHITE : BLACK;
    }
}

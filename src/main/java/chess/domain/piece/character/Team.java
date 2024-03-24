package chess.domain.piece.character;

public enum Team {
    BLACK(-1),
    WHITE(1),
    ;

    private final int attackDirection;
    Team(int attackDirection) {
        this.attackDirection = attackDirection;
    }

    public Team opponent() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
        };
    }

    public int attackDirection() {
        return attackDirection;
    }
}

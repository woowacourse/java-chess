package chess.domain.piece.character;

public enum Team {
    BLACK,
    WHITE,
    ;

    public Team opponent() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
        };
    }
}

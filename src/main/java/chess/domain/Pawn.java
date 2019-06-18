package chess.domain;

public class Pawn implements Piece{
    private final boolean teamColor;

    public Pawn(boolean teamColor) {
        this.teamColor = teamColor;
    }
}

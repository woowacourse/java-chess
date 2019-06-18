package chess.domain;

public class Queen implements Piece{
    private final boolean teamColor;

    public Queen(boolean teamColor) {
        this.teamColor = teamColor;
    }
}

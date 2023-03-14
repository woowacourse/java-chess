package domain.piece;

public abstract class Piece {

    private final TeamColor teamColor;

    protected Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean isBlack() {
        return teamColor == TeamColor.BLACK;
    }
}

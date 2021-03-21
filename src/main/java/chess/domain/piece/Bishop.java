package chess.domain.piece;

public class Bishop extends Piece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    public Bishop(TeamType teamType) {
        super(NAME, teamType, new Score(SCORE), Direction.getBishopDirections());
    }
}

package chess.domain.piece;

public class Queen extends Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    public Queen(TeamType teamType) {
        super(NAME, teamType, SCORE, Direction.getQueenDirections());
    }
}

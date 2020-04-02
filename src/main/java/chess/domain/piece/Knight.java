package chess.domain.piece;

import chess.domain.Team;

public class Knight extends PieceNotPawn {

    private static final String EXPRESSION = "n";

    public Knight(Team team) {
        super(team, EXPRESSION, 2.5);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isKnightMove();
    }
}

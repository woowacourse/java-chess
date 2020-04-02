package chess.domain.piece;

import chess.domain.Team;

public class King extends PieceNotPawn {

    private static final String EXPRESSION = "k";

    public King(Team team) {
        super(team, EXPRESSION, 0.0);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return moveInformation.isEndAdjacentToStart() && !moveInformation.isSameTeamPlacedOnEnd();
    }
}

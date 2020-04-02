package chess.domain.piece;

import chess.domain.Team;

public class Rook extends PieceNotPawn {

    private static final String EXPRESSION = "r";

    public Rook(Team team) {
        super(team, EXPRESSION, 5.0);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isEndOnStraightLineOfStart()
            && !moveInformation.doAnyPieceExistInBetween();
    }
}

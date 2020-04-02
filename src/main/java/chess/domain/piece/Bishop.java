package chess.domain.piece;

import chess.domain.Team;

public class Bishop extends PieceNotPawn {

    private static final String EXPRESSION = "b";

    public Bishop(Team team) {
        super(team, EXPRESSION, 3.0);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && moveInformation.isEndOnDiagonalOfStart();
    }
}

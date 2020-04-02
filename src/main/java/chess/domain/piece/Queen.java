package chess.domain.piece;

import chess.domain.Team;

public class Queen extends PieceNotPawn {

    private static final String EXPRESSION = "q";

    public Queen(Team team) {
        super(team, EXPRESSION, 9.0);
    }

    @Override
    public boolean canMove(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && (moveInformation.isEndOnDiagonalOfStart()
            || moveInformation.isEndOnStraightLineOfStart());
    }
}

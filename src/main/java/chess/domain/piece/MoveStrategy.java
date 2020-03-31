package chess.domain.piece;

@FunctionalInterface
public interface MoveStrategy {

    boolean test(MoveInformation moveInformation);

    static boolean testKing(MoveInformation moveInformation) {
        return moveInformation.isEndAdjacentToStart() && !moveInformation.isSameTeamPlacedOnEnd();
    }

    static boolean testQueen(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && (moveInformation.isEndOnDiagonalOfStart()
            || moveInformation.isEndOnStraightLineOfStart());
    }

    static boolean testBishop(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && !moveInformation.doAnyPieceExistInBetween()
            && moveInformation.isEndOnDiagonalOfStart();
    }

    static boolean testKnight(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isKnightMove();
    }

    static boolean testRook(MoveInformation moveInformation) {
        return !moveInformation.isSameTeamPlacedOnEnd()
            && moveInformation.isEndOnStraightLineOfStart()
            && !moveInformation.doAnyPieceExistInBetween();
    }

    static boolean testPawn(MoveInformation moveInformation) {
        if (moveInformation.isEndOnDiagonalOfStart()) {
            return moveInformation.isMoveForward()
                && moveInformation.isEndAdjacentToStart()
                && moveInformation.isEnemyOnEnd();
        }
        if (moveInformation.isStraightMoveBy(1) && moveInformation.isMoveForward()) {
            return !moveInformation.doAnyPieceExistOnEnd();
        }
        /* 두 칸 전진시 */
        return moveInformation.isStraightMoveBy(2)
            && moveInformation.isStartOnInitialPosition()
            && !moveInformation.doAnyPieceExistInBetween()
            && !moveInformation.doAnyPieceExistOnEnd();
    }
}

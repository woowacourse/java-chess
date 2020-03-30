package chess.piece;

import chess.position.Position;
import chess.position.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final int MINIMUM_MOVEMENT_RANK = 1;
    private static final int MAXIMUM_MOVEMENT_RANK = 2;

    private boolean hasMoved;

    public Pawn(Team team) {
        super(team, "P");
        hasMoved = false;
    }

//    private boolean isNotMoveOneOrTwoSquaresForward(Position start, Position end) {
//        return !isMoveOneOrTwoSquaresForward(start, end);
//    }

//    private boolean isMoveOneOrTwoSquaresForward(Position start, Position end) {
//        int increaseAmountOfRank = start.increaseAmountOfRank(end);
//        return start.isSameFile(end) && validateTeamAndIncreaseAmount(increaseAmountOfRank);
//    }

//    private boolean validateTeamAndIncreaseAmount(int increaseAmountOfRank) {
//        int differenceBetweenTwoRanks = team.isWhite() ? increaseAmountOfRank : -1 * increaseAmountOfRank;
//        return differenceBetweenTwoRanks >= MINIMUM_MOVEMENT_RANK && differenceBetweenTwoRanks <= MAXIMUM_MOVEMENT_RANK;
//    }

//    private boolean isNotAbleToMoveDoubleSquare(Position start, Position end) {
//        return !isAbleToMoveDoubleSquare(start, end);
//    }

//    private boolean isAbleToMoveDoubleSquare(Position start, Position end) {
//        return !hasMoved && start.differenceOfFile(end) != MAXIMUM_MOVEMENT_RANK;
//    }

    private boolean isValidMovementWithoutConsideringOtherPieces(Position source, Position target) {
        List<Position> movablePositionsWithoutConsideringOtherPieces = new ArrayList<>();
        if (team.isWhite()) {
            if (!hasMoved) {
                movablePositionsWithoutConsideringOtherPieces.add(source.getUpUp());
            }
            //위에3칸
        }
        if (team.isBlack()) {
            if (!hasMoved) {
                movablePositionsWithoutConsideringOtherPieces.add(source.getDownDown());
            }
            //아래3칸
        }
        return movablePositionsWithoutConsideringOtherPieces.contains(target);
    }

    @Override
    public void updateHasMoved() {
        this.hasMoved = true;
    }

    @Override
    public boolean isInvalidMovementWithoutConsideringOtherPieces(Position source, Position target) {
        //TODO: 폰의 움직임을 정하려면 board가 필요하다... 이를 해결하자
//        return isNotMoveOneOrTwoSquaresForward(source, target) || isNotAbleToMoveDoubleSquare(source, target);
        return !isValidMovementWithoutConsideringOtherPieces(source, target);
    }

    @Override
    public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
        if (start.differenceOfRank(end) == MAXIMUM_MOVEMENT_RANK) {
            List<Rank> ranks = Rank.valuesBetween(start.getRank(), end.getRank());
            return ranks.stream()
                    .map(rank -> Position.of(start.getFile(), rank))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

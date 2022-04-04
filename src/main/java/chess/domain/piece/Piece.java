package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;

public abstract class Piece {

    private final Camp camp;
    private final PieceProperty pieceProperty;

    protected Piece(final Camp camp, final PieceProperty pieceProperty) {
        this.camp = camp;
        this.pieceProperty = pieceProperty;
    }

    public final boolean isBlack() {
        return camp == Camp.BLACK;
    }

    public abstract void move(Position beforePosition,
                              Position afterPosition,
                              Consumer<Piece> movePiece);

    public abstract void move(final Positions positions,
                              final Consumer<Piece> movePiece);

    public abstract boolean canMove(Position beforePosition, Position afterPosition);

    public abstract boolean canMove(Positions positions);

    public abstract boolean isNullPiece();

    public abstract List<UnitDirectVector> getPossibleDirections();

    public boolean isSameCampWith(final Camp otherCamp) {
        return camp == otherCamp;
    }

    public boolean isSameCampWith(final Piece targetPiece) {
        return isSameCampWith(targetPiece.camp);
    }

    public final PieceProperty getPieceProperty() {
        return pieceProperty;
    }

    public final double getScore() {
        return pieceProperty.getScore();
    }

    public final String getCharacter() {
        return pieceProperty.getCharacter();
    }

    Camp getCamp() {
        return camp;
    }

    public SortedMap<UnitDirectVector, List<Position>> findMovableDirections(final Positions positions,
                                                                             final Predicate<Position> isNullPiece) {
//        // 4-2 가능한 방향 마다 -> beforePosition + 가능한 단위벡터 1개씩 -> 가능한 afterPosition 1개씩을 얻어낸다.
//        final List<UnitDirectVector> possibleDirections = getPossibleDirections();
//
//        // 4-2-1 가능한 방향과 현재방향을 비교해서 걸리는게 하나라도 있어야 유효한 방향이다.
////        if (possibleDirections.stream()
////            .noneMatch(possibleDirection -> possibleDirection.isSameUnitDirectVector(positions))) {
////            throw new IllegalArgumentException("움직이려는 방향이 유효하지 않습니다.");
////        }
//
//        final List<Position> validNextPositions = new ArrayList<>();
//        // 4-2-2 현재방향과 무관하게, 갈수 있는 방향들 -> 방향별로 가면서, 유효한 afterposition을 받아와보자.
//        Position currentPosition = positions.before();
//        for (UnitDirectVector possibleDirection : possibleDirections) {
//            Position initPosition = currentPosition;
//            //(4-4-4) 2개 row, column valid 조건을 제일 바깥에서 묶는 메서드
//            while (initPosition.isNextValid(possibleDirection)) {
//                final Position nextPosition = initPosition.calculatePossibleAfterPositions(possibleDirection);
//                validNextPositions.add(nextPosition);
//                System.out.println("nextPosition = " + nextPosition + " 이 유효한 다음 position으로 추가됩니다.");
//                initPosition = nextPosition;
//            }
//        }
//        return validNextPositions;
//
        //7. list로 다 묶으면 짤리는 부분을 알 수 없다 -> 방향별로 묶어서 맵으로 만들기?
        final List<UnitDirectVector> possibleDirections = getPossibleDirections();
        Position initPosition = positions.before();

        final SortedMap<UnitDirectVector, List<Position>> possiblePositionsByDirection = new TreeMap<>();

        for (UnitDirectVector possibleDirection : possibleDirections) {
            Position currentPosition = initPosition;
            final List<Position> validNextPositions = new ArrayList<>();

            while (currentPosition.isNextValid(possibleDirection)) {
                final Position nextPosition = currentPosition.calculatePossibleAfterPositions(possibleDirection);

                //8.
                if (isNullPiece.apply(nextPosition) &&
                    //9. 추가조건으로 canMove( before, after)까지 줘서 끊어보자.
                    canMove(initPosition, nextPosition)
                ) {
                    validNextPositions.add(nextPosition);
                    currentPosition = nextPosition;
                    continue;
                }
                System.out.println("다음위치후보였지만, 비지않은 곳이라서 끊는다.");
                break;
            }
            possiblePositionsByDirection.put(possibleDirection, validNextPositions);
        }
        return possiblePositionsByDirection;
    }
}

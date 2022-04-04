package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.board.UnitDirectVector;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

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

    public abstract boolean checkCanMoveByDistance(Position beforePosition, Position afterPosition);

    public abstract boolean checkCanMoveByDistance(Positions positions);

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

    public SortedMap<UnitDirectVector, List<Position>> findMovablePositionsByDirection(final Position position,
                                                                                       final Predicate<Position> isNullPiece) {
        final List<UnitDirectVector> possibleDirections = getPossibleDirections();
        final SortedMap<UnitDirectVector, List<Position>> possiblePositionsByDirection = new TreeMap<>();

        for (UnitDirectVector possibleDirection : possibleDirections) {
            Position currentPosition = position;
            final List<Position> validNextPositions = new ArrayList<>();

            while (currentPosition.isNextValid(possibleDirection)) {
                final Position nextPosition = currentPosition.calculatePossibleAfterPositions(possibleDirection);

                if (isNullPieceInitToNextPosition(isNullPiece, position, nextPosition) &&
                    isNullPiece.test(nextPosition) &&
                    checkCanMoveByDistance(position, nextPosition)
                ) {
                    validNextPositions.add(nextPosition);
                    currentPosition = nextPosition;
                    continue;
                }
                break;
            }
            possiblePositionsByDirection.put(possibleDirection, validNextPositions);
        }
        return possiblePositionsByDirection;
    }

    private boolean isNullPieceInitToNextPosition(final Predicate<Position> isNullPiece,
                                                  final Position initPosition,
                                                  final Position nextPosition) {
        return initPosition.pathTo(nextPosition)
            .stream()
            .allMatch(position -> isNullPiece.test(position));
    }
}

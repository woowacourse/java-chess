package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Optional;

public class Bishop implements Piece{

    private Position position;
    private final Color color;
    private final List<Movement> movements = List.of(Movement.RIGHT_UP,Movement.LEFT_UP,Movement.RIGHT_DOWN,Movement.LEFT_DOWN);

    public Bishop(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void move(Position wantedPosition, List<Piece> wantedPositionExistPieces) {
        for (Movement movement : movements){
            this.position = movementWhenPossible(movement,wantedPosition,wantedPositionExistPieces);
            if(position.equals(wantedPosition)) {
                return;
            }
        }
        if(!position.equals(wantedPosition)){
            throw new InvalidMoveException();
        }
    }

    private Position movementWhenPossible(Movement movement, Position wantedPosition,
                                          List<Piece> wantedPositionExistPiece) {
        Position temporalPosition = position;
        while (temporalPosition.canMove(movement)){
            temporalPosition = temporalPosition.move(movement);
            if(temporalPosition.equals(wantedPosition) && isPositionDoesntExistSameColor(wantedPositionExistPiece, temporalPosition)){
                return temporalPosition;
            }
            if(isSamePositionPieceExist(wantedPositionExistPiece, temporalPosition)){
                throw new InvalidMoveException();
            }
        }
        return position;
    }

    private boolean isPositionDoesntExistSameColor(List<Piece> wantedPositionExistPiece, Position temporalPosition) {
        Optional<Piece> otherPieceByPosition = findOtherPieceByPosition(temporalPosition, wantedPositionExistPiece);
        if(otherPieceByPosition.isEmpty()){
            return true;
        }
        return isOpposite(otherPieceByPosition.get());
    }

    private Optional<Piece> findOtherPieceByPosition(Position temporalPosition, List<Piece> wantedPositionExistPiece) {
        for(Piece piece : wantedPositionExistPiece){
            if(piece.getPosition().equals(temporalPosition)){
                return Optional.of(piece);
            }
        }
        return Optional.empty();
    }

    private boolean isSamePositionPieceExist(List<Piece> wantedPositionExistPiece, Position temporalPosition) {
        for(Piece piece : wantedPositionExistPiece){
            if(piece.getPosition().equals(temporalPosition)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isOpposite(Piece piece) {
        return color.opposite() == piece.getColor();
    }

    @Override
    public Color getColor() {
        return color;
    }
}

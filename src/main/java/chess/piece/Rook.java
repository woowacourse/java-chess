package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Rook implements Piece{

    private Position position;
    private final Color color;
    private static final List<Movement> movements = List.of(Movement.UP,Movement.DOWN,Movement.LEFT,Movement.RIGHT);
    private final PieceType pieceType;

    public Rook(Position position, Color color) {
        this.position = position;
        this.color = color;
        pieceType = PieceType.ROOK;
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
            if(isSamePosition(piece)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public boolean isOpposite(Piece piece) {
        return color.opposite() == piece.getColor();
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isSamePosition(Piece otherPiece) {
        return otherPiece.getPosition().equals(position);
    }

    @Override
    public PieceType pieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rook rook = (Rook) o;
        return Objects.equals(position, rook.position) && color == rook.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }
}

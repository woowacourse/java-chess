package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Objects;

public class King implements Piece{

    private Position position;
    private final Color color;
    private final PieceType pieceType;
    private static final List<Movement> movements = List.of(Movement.UP,Movement.DOWN,Movement.LEFT,Movement.RIGHT);

    public King(Position position, Color color) {
        this.position = position;
        this.color = color;
        this.pieceType = PieceType.KING;
    }

    @Override
    public void move(Position wantedPosition, List<Piece> wantedPositionExistPiece) {
        boolean isWantedPositionExistSameColor = isWantedPositionIsNotSameColor(wantedPositionExistPiece);

        if(!isWantedPositionExistSameColor){
            throw new InvalidMoveException();
        }
        for(Movement movement : movements){
            var temporalPosition = moveWhenPossible(movement);
            if(temporalPosition.equals(wantedPosition)){
                position = temporalPosition;
                return;
            }
        }
        throw new InvalidMoveException();
    }

    private boolean isWantedPositionIsNotSameColor(List<Piece> wantedPositionExistPiece) {
        if(wantedPositionExistPiece.isEmpty()){
            return true;
        }

        Piece first = wantedPositionExistPiece.getFirst();
        return this.isOpposite(first);
    }

    private Position moveWhenPossible(Movement movement) {
        var temporalPosition = position;
        if(temporalPosition.canMove(movement)){
            return temporalPosition.move(movement);
        }
        return position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isOpposite(Piece piece) {
        Color oppositeColor = this.color.opposite();
        return oppositeColor.equals(piece.getColor());
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean isSamePosition(Piece otherPiece) {
        return otherPiece.getPosition().equals(position);
    }

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
        King king = (King) o;
        return Objects.equals(position, king.position) && color == king.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }
}

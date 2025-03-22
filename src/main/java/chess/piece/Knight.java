package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.exception.InvalidMoveException;
import java.util.List;
import java.util.Objects;

public class Knight implements Piece{

    private Position position;
    private static final List<Movement> movements = List.of(
            Movement.DOWN_DOWN_LEFT, Movement.DOWN_DOWN_RIGHT,
            Movement.RIGHT_RIGHT_DOWN, Movement.RIGHT_RIGHT_UP,
            Movement.LEFT_LEFT_DOWN, Movement.LEFT_LEFT_UP,
            Movement.UP_UP_LEFT, Movement.UP_UP_RIGHT
    );
    private final Color color;

    public Knight(Position position, Color color) {
        this.position = position;
        this.color = color;
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

    private Position moveWhenPossible(Movement movement) {
        var temporalPosition = position;
        if(temporalPosition.canMove(movement)){
            return temporalPosition.move(movement);
        }
        return position;
    }

    private boolean isWantedPositionIsNotSameColor(List<Piece> wantedPositionExistPiece) {
        if(wantedPositionExistPiece.isEmpty()){
            return true;
        }

        Piece first = wantedPositionExistPiece.getLast();
        return this.isOpposite(first);
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

    @Override
    public boolean isSamePosition(Piece otherPiece) {
        return otherPiece.getPosition().equals(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Knight knight = (Knight) o;
        return Objects.equals(position, knight.position) && color == knight.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, color);
    }
}

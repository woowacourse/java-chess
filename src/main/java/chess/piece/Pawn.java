package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.Row;
import chess.exception.InvalidMoveException;
import java.util.List;

public class Pawn implements Piece{

    private static final int START_MOVE_WEIGHT = 2;
    private static final int NOT_START_MOVE_WEIGHT = 1;
    private static final Movement BLACK_MOVEMENT = Movement.DOWN;
    private static final List<Movement> BLACK_ATTACK_MOVEMENTS = List.of(Movement.LEFT_DOWN,Movement.RIGHT_DOWN);
    private static final Movement WHITE_MOVEMENT = Movement.UP;
    private static final List<Movement> WHITE_ATTACK_MOVEMENTS = List.of(Movement.LEFT_UP,Movement.RIGHT_UP);

    private final Row startRow;
    private final Movement onlyMoveMovement;
    private final List<Movement> attackMovement;
    private final Color color;
    private Position position;


    public Pawn(Position position, Color color) {
        this.color = color;
        this.position = position;
        startRow = position.row();
        if(color == Color.BLACK){
            onlyMoveMovement = BLACK_MOVEMENT;
            attackMovement = BLACK_ATTACK_MOVEMENTS;
            return;
        }
        onlyMoveMovement = WHITE_MOVEMENT;
        attackMovement = WHITE_ATTACK_MOVEMENTS;
    }

    private Position attackMove(Piece wantedPositionExistPiece, Position wantedPosition) {
        for(Movement movement : attackMovement){
            var temporalPosition = moveWhenPossible(movement);
            if(temporalPosition.equals(wantedPositionExistPiece.getPosition()) && wantedPositionExistPiece.isOpposite(this)){
                return wantedPosition;
            }
        }
        throw new InvalidMoveException();
    }

    private Position moveWhenPossible(Movement movement) {
        if(position.canMove(movement)){
            return position.move(movement);
        }
        return position;
    }

    private Position onlyMove(int startMoveWeight, Position wantedPosition) {
        Position temporalPosition = position;
        for (int i = 0; i < startMoveWeight; i++) {
            temporalPosition = temporalPosition.move(onlyMoveMovement);
            if(temporalPosition.equals(wantedPosition)){
                return temporalPosition;
            }
        }
        throw new InvalidMoveException();
    }

    @Override
    public void move(Position wantedPosition, List<Piece> wantedPositionExistPiece) {
        if(!wantedPositionExistPiece.isEmpty()){
            this.position = attackMove(wantedPositionExistPiece.getFirst(),wantedPosition);
            return;
        }
        if(isPawnStartMove()){
            this.position = onlyMove(START_MOVE_WEIGHT,wantedPosition);
            return;
        }
        this.position = onlyMove(NOT_START_MOVE_WEIGHT,wantedPosition);
    }

    private boolean isPawnStartMove() {
        return startRow.equals(position.row());
    }

    public boolean isOpposite(Piece piece) {
        return color.opposite() == piece.getColor();
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}

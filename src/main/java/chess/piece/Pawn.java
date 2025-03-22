package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Pawn extends Piece {

    private static final Map<Color, List<Movement>> movementForColor;

    static {
        movementForColor = Map.of(
                Color.WHITE,
                List.of(Movement.UP, Movement.UP_UP,Movement.RIGHT_UP,Movement.LEFT_UP),
                Color.BLACK,
                List.of(Movement.DOWN, Movement.DOWN_DOWN,Movement.RIGHT_DOWN,Movement.LEFT_DOWN)
        );
    }

    private boolean hasMoved;

    protected Pawn(Color color, Position position) {
        super(color, position);
        hasMoved = false;
    }

    public boolean canMove(Position position, List<Piece> pieces) {
        List<Movement> movements = movementForColor.get(this.color);

        Optional<Movement> findMovement = getFindMovement(position, movements);

        if (findMovement.isEmpty()) {
            return false;
        }

        Movement movement = findMovement.get();
        boolean hasFriendlyPiece = pieces.stream().anyMatch(piece -> piece.isSamePosition(position));
        if (movement.equals(Movement.UP_UP) || movement.equals(Movement.DOWN_DOWN)) {
            return !hasMoved && !hasFriendlyPiece;
        }

        if(!(movement.equals(Movement.UP) || movement.equals(Movement.DOWN))){
            return pieces.stream().anyMatch(piece -> piece.isSamePosition(position) && piece.isAnotherTeam(this));
        }
        return !hasFriendlyPiece;
    }

    private Optional<Movement> getFindMovement(Position position, List<Movement> movements) {
        return movements.stream().filter(movement -> {
            boolean canMove = this.position.canMove(movement);
            if(!canMove){
                return false;
            }
            Position newPosition = this.position.move(movement);
            return newPosition.isSamePosition(position);
        }).findAny();
    }

    public void moveTo(Position position, List<Piece> pieces) {
        if (!canMove(position,pieces)) {
            throw new IllegalArgumentException("해당 자리에 이동할 수 없습니다.");
        }
        List<Movement> movements = movementForColor.get(this.color);
        Optional<Movement> findMovement = getFindMovement(position, movements);
        if (findMovement.isEmpty()) {
            throw new IllegalArgumentException("해당 자리에 이동할 수 없습니다.");
        }
        this.position = this.position.move(findMovement.get());
        hasMoved = true;
    }


}

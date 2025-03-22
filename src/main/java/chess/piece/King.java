package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.List;
import java.util.Optional;

public class King extends Piece {

    private static final List<Movement> movements;

    static {
        movements = List.of(Movement.DOWN,Movement.UP,Movement.LEFT,Movement.RIGHT,
                Movement.RIGHT_UP,Movement.LEFT_UP,Movement.RIGHT_DOWN,Movement.LEFT_DOWN);
    }

    protected King(Color color, Position position) {
        super(color, position);
    }
    @Override
    public boolean canMove(Position position, List<Piece> pieces) {
        Optional<Movement> findMovement = getFindMovement(position, movements);

        if (findMovement.isEmpty()) {
            return false;
        }

        boolean hasPiece = pieces.stream().anyMatch(piece -> piece.isSamePosition(position));

        if(hasPiece){
            return pieces.stream().anyMatch(piece -> piece.isSamePosition(position) && piece.isAnotherTeam(this));
        }
        return true;
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
    @Override
    public void moveTo(Position position, List<Piece> pieces) {
        if (!canMove(position,pieces)) {
            throw new IllegalArgumentException("해당 자리에 이동할 수 없습니다.");
        }
        Optional<Movement> findMovement = getFindMovement(position, movements);
        if (findMovement.isEmpty()) {
            throw new IllegalArgumentException("해당 자리에 이동할 수 없습니다.");
        }
        this.position = this.position.move(findMovement.get());
    }
}

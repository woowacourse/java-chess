package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bishop extends Piece {

    private static final List<Movement> movements;

    static {
        movements = List.of(Movement.LEFT_UP,Movement.RIGHT_UP,Movement.LEFT_DOWN,Movement.RIGHT_DOWN);
    }

    protected Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean canMove(Position position, List<Piece> pieces) {
        Optional<Movement> findMovement = getFindMovement(position, movements);

        if (findMovement.isEmpty()) {
            return false;
        }

        Movement movement = findMovement.get();
        List<Position> paths = getPath(position, movement);

        boolean hasIntermediatePieces = hasIntermediatePieces(pieces, paths);

        if(hasIntermediatePieces){
            return false;
        }

        boolean hasPiece = pieces.stream().anyMatch(piece -> piece.isSamePosition(position));

        if(hasPiece){
            return pieces.stream().anyMatch(piece -> piece.isSamePosition(position) && piece.isAnotherTeam(this));
        }
        return true;
    }

    private boolean hasIntermediatePieces(List<Piece> pieces, List<Position> paths) {
        for (Piece piece : pieces) {
            for (Position path : paths) {
                if(piece.isSamePosition(path)){
                    return true;
                }
            }
        }
        return false;
    }

    private Optional<Movement> getFindMovement(Position position, List<Movement> movements) {
        for (Movement movement : movements) {
            Position nowPosition = this.position;
            while(nowPosition.canMove(movement)){
                nowPosition = nowPosition.move(movement);
                if(nowPosition.equals(position)){
                    return Optional.of(movement);
                }
            }
        }
        return Optional.empty();
    }

    private List<Position> getPath(Position endPosition,Movement movement){
        List<Position> path = new ArrayList<>();
        Position nowPosition = this.position.move(movement);
        while(!nowPosition.equals(endPosition)){
            path.add(nowPosition);
            nowPosition = nowPosition.move(movement);
        }
        return path;
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

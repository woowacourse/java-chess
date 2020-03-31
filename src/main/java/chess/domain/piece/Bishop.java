package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Position position, Team team) {
        super(position, team);
        this.representation = 'B';
        this.score = 3;
    }

    @Override
    protected void validateMove(Position destination) {
        if (this.position.isNonDiagonalDirection(destination)) {
            throw new IllegalMoveException(ILLEGAL_MOVE);
        }
    }

    @Override
    public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
        validateNoObstacle(piecesInBetween);
    }
}

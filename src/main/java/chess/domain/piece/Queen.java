package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.List;

public class Queen extends Piece {

    public Queen(Position position, Team team) {
        super(position, team);
        this.representation = 'Q';
        this.score = 9;
    }

    @Override
    protected void validateMove(Position destination) {
        if (this.position.isNonDiagonalDirection(destination) && this.position.isNonLinearDirection(destination)) {
            throw new IllegalMoveException(ILLEGAL_MOVE);
        }
    }

    @Override
    public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
        validateNoObstacle(piecesInBetween);
    }
}

package chess.domain.piece;

import chess.domain.Position;
import chess.exception.IllegalMoveException;

import java.util.List;

public class Rook extends Piece {

	public Rook(Position position, Team team) {
		super(position, team);
		this.representation = 'R';
		this.score = 5;
	}

	@Override
	protected void validateMove(Position destination) {
		if (this.position.isNonLinearDirection(destination)) {
			throw new IllegalMoveException(ILLEGAL_MOVE);
		}
	}

	@Override
	public void validateDestination(Position destination, Piece destinationPiece, List<Piece> piecesInBetween) {
		validateNoObstacle(piecesInBetween);
	}
}

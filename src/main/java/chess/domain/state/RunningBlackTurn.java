package chess.domain.state;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningBlackTurn extends Running {

	private static final Color COLOR = Color.BLACK;

	RunningBlackTurn(Map<Position, Piece> pieces) {
		super(pieces);
	}

	@Override
	public GameState proceed(Command command) {
		return executeMovingPiece(command, COLOR);
	}

	@Override
	protected Running getOpponentTurn() {
		return new RunningWhiteTurn(board.getPieces());
	}

	@Override
	public Color getColor() {
		return COLOR;
	}
}

package chess.domain.state;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningWhiteTurn extends Running {

	private static final Color COLOR = Color.WHITE;

	RunningWhiteTurn(Map<Position, Piece> pieces) {
		super(pieces);
	}

	@Override
	protected Running getOpponentTurn() {
		return new RunningBlackTurn(board.getPieces());
	}

	@Override
	public Color getColor() {
		return COLOR;
	}
}

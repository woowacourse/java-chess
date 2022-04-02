package chess.domain.state;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningBlackTurn extends Running {

	private static final Color COLOR = Color.BLACK;

	public RunningBlackTurn(Map<Position, Piece> pieces) {
		super(pieces);
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

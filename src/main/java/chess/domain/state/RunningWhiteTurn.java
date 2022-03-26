package chess.domain.state;

import java.util.Map;
import java.util.Optional;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningWhiteTurn extends Running {

	public RunningWhiteTurn(Map<Position, Piece> pieces) {
		super(pieces);
	}

	@Override
	public State proceed(Command command) {
		if (!command.isMove()) {
			return checkFinished(command);
		}

		if (!board.isWhite(command.getFromPosition())) {
			throw new IllegalArgumentException();
		}

		board.movePiece(command.getFromPosition(), command.getToPosition());
		return new RunningBlackTurn(board.getPieces());
	}
}

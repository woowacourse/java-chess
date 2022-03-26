package chess.domain.state;

import java.util.Map;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningBlackTurn extends Running {

	public RunningBlackTurn(Map<Position, Piece> pieces) {
		super(pieces);
	}

	@Override
	public State proceed(Command command) {
		if (!command.isMove()) {
			return checkFinished(command);
		}

		if (board.isWhite(command.getFromPosition())) {
			throw new IllegalArgumentException();
		}

		board.movePiece(command.getFromPosition(), command.getToPosition());
		return new RunningWhiteTurn(this.board.getPieces());
	}
}

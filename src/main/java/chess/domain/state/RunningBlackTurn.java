package chess.domain.state;

import java.util.Map;

import chess.domain.position.Position;
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
			throw new IllegalArgumentException(CANNOT_MOVE_OPPONENT_PIECE);
		}

		board.movePiece(command.getFromPosition(), command.getToPosition());
		return new RunningWhiteTurn(this.board.getPieces());
	}
}

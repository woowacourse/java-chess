package chess.domain.state;

import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.command.Command;
import chess.domain.piece.Piece;

public class RunningWhiteTurn extends Running {

	RunningWhiteTurn(Map<Position, Piece> pieces) {
		super(pieces);
	}

	@Override
	public GameState proceed(Command command) {
		if (!command.isMove()) {
			return checkFinished(command);
		}
		validateMoveOpponent(command);
		board.movePiece(command.getFromPosition(), command.getToPosition());
		return checkBlackKingExist();
	}

	private void validateMoveOpponent(Command command) {
		if (!board.isWhite(command.getFromPosition())) {
			throw new IllegalStateException(CANNOT_MOVE_OPPONENT_PIECE);
		}
	}

	private GameState checkBlackKingExist() {
		if (board.isKingNotExist(Color.BLACK)) {
			return new Finished(this.board.getPieces());
		}
		return new RunningBlackTurn(board.getPieces());
	}

	@Override
	public Color getColor() {
		return Color.WHITE;
	}
}

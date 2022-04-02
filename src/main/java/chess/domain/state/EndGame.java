package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Team;

public final class EndGame extends Finished {

	EndGame(final Board board, final Team winner) {
		super(board, winner);
	}
}

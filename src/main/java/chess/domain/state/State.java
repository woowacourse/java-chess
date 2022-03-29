package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.result.StatusResult;

public interface State {

	State start(Board board);

	State play(Position source, Position target);

	StatusResult createStatus();

	State finish();

	boolean isFinished();

	Team judgeWinner();

	Board getBoard();
}

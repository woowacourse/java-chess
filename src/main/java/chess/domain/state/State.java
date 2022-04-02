package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import chess.domain.score.ScoreResult;

public interface State {

	State start(Board board);

	State play(Position source, Position target);

	ScoreResult createStatus();

	State finish();

	boolean isFinished();

	Team judgeWinner();

	Board getBoard();
}

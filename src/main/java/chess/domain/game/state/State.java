package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.game.Score;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public interface State {
	State start();

	State end();

	State move(Position source, Position target);

	Board board();

	Score score(Color color);

	boolean isFinished();
}

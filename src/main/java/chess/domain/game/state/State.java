package chess.domain.game.state;

import chess.domain.game.Board;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public interface State {
	State start();

	State end();

	State move(Position source, Position target);

	Board board();

	Score score(Color color);

	Turn turn();

	boolean isFinished();
}

package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public interface State {

	boolean isFinished();

	State play(Piece source, Piece target);

	State finish();
	
	Team getTeam();
}

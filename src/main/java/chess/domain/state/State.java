package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessStatus;
import chess.domain.Side;
import chess.domain.position.Position;

public interface State {
	State move(Position source, Position target);

	ChessStatus createStatus();

	boolean isEnd();

	State end();

	ChessBoard getChessBoard();

	Side getTurn();
}

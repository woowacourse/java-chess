package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.ChessStatus;
import chess.domain.position.Position;

public interface State {
	State move(Position source, Position target);

	ChessStatus calculateStatus();

	boolean isEnd();

	State end();

	ChessBoard getChessBoard();
}

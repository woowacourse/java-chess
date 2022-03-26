package chess.domain.command;

import chess.domain.Position;

public interface Command {

	boolean isStart();
	boolean isEnd();
	boolean isMove();
	Position getFromPosition();
	Position getToPosition();
}

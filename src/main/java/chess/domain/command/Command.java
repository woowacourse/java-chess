package chess.domain.command;

import chess.domain.position.Position;

public interface Command {

	boolean isStart();
	boolean isMove();
	Position getFromPosition();
	Position getToPosition();
}

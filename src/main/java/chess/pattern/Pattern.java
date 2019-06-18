package chess.pattern;

import chess.Path;
import chess.Position;

public interface Pattern {
	Path move(Position start, Position end);
}

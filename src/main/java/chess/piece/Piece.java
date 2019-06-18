package chess.piece;

import chess.Path;
import chess.Position;

public interface Piece {
	Path move(Position position);
}

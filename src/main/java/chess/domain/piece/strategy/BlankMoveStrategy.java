package chess.domain.piece.strategy;

import java.util.Collections;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;

public class BlankMoveStrategy implements MoveStrategy {
	@Override
	public Path findMovablePositions(final Path path, final Map<Position, Piece> pieces) {
		path.findPathOneTimeByDirections(Collections.EMPTY_LIST, pieces);
		return path;
	}
}

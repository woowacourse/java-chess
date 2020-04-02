package chess.domain.piece.strategy;

import java.util.List;
import java.util.Map;

import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Path;
import chess.domain.piece.Piece;

public class
BasicOneMoveStrategy implements MoveStrategy {
	List<Direction> directions;

	public BasicOneMoveStrategy(List<Direction> directions) {
		this.directions = directions;
	}

	@Override
	public Path findMovablePositions(Path path, Map<Position, Piece> pieces) {
		path.findPathOneTimeByDirections(directions, pieces);
		return path;
	}
}

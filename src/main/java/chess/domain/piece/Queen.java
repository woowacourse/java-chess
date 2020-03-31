package chess.domain.piece;

import java.util.LinkedList;
import java.util.List;

import chess.domain.board.Position;
import chess.domain.piece.state.State;

/**
 *    퀸을 의미하는 클래스입니다.
 *
 *    @author AnHyungJu, LeeHoBin
 */
public class Queen extends Piece {
	public Queen(State state, String symbol) {
		super(state, symbol);
	}

	@Override
	public List<Position> movingTrace(Position source, Position target) {
		if (state.isCaptured()) {
			throw new UnsupportedOperationException("죽은 말은 움직일 수 없습니다.");
		}

		return findRoute(source, target);
	}

	protected List<Position> findRoute(Position source, Position target) {
		List<Position>[] possiblePositions = source.nextPositions(Direction.EVERY_DIRECTION);

		return makeRoute(source, target, findTargetRoute(target, possiblePositions));
	}

	private List<Position> findTargetRoute(Position target, List<Position>[] possiblePositions) {
		boolean isPossible = false;
		int possibleIndex = 0;

		for (int i = 0; i < possiblePositions.length; i++) {
			if (possiblePositions[i].stream()
				.anyMatch(position -> position.equals(target))) {
				isPossible = true;
				possibleIndex = i;
				break;
			}
		}
		if (!isPossible) {
			throw new UnsupportedOperationException("갈 수 없는 target입니다.");
		}
		return possiblePositions[possibleIndex];
	}

	private List<Position> makeRoute(Position source, Position target, List<Position> targetRoute) {
		List<Position> route = new LinkedList<>();

		route.add(source);
		for (Position position : targetRoute) {
			if (position.equals(target)) {
				break;
			}
			route.add(position);
		}
		route.add(target);
		return route;
	}

	@Override
	public double score() {
		return 9D;
	}
}

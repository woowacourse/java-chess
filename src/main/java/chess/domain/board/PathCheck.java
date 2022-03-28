package chess.domain.board;

import java.util.List;
import java.util.function.Predicate;

public class PathCheck {
	public static boolean check(Position beforePosition,
		Position afterPosition,
		Predicate<Position> positionPredicate) {
		List<Position> path = beforePosition.pathTo(afterPosition);
		return path.stream()
			.allMatch(positionPredicate);
	}
}

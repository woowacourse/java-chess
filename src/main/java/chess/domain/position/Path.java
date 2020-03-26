package chess.domain.position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
	private final List<Position> path;

	private Path(List<Position> path) {
		this.path = path;
	}

	public static Path of(Position start, Position end) {
		List<Position> path = new ArrayList<>();

		Position current = start;
		for (int i = 1; i < Distance.of(start, end).getDistance(); i++) {
			current = Direction.of(current, end).move(current);
			path.add(current);
		}
		return new Path(path);
	}

	public List<String> path() {
		return path.stream()
			.map(Position::getName)
			.collect(Collectors.toUnmodifiableList());
	}
}

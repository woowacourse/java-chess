package chess;

import java.util.ArrayList;
import java.util.List;

public class Path {
	private final List<Position> path;

	public Path(final List<Position> path) {
		this.path = new ArrayList<>(path);
	}
}

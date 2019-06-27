package chess.domain;

import java.util.*;

public class Path {
	private final List<Position> path;

	public Path() {
		path = new ArrayList<>();
	}

	public List<Position> getPath() {
		return Collections.unmodifiableList(path);
	}

	public void add(final Position position) {
		path.add(position);
	}

	public boolean contains(Position end) {
		return path.contains(end);
	}

	public void removeEndPosition() {
		path.remove(path.size() - 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Path)) {
			return false;
		}
		final Path path1 = (Path) o;
		return Objects.equals(path, path1.path);
	}

	@Override
	public int hashCode() {
		return Objects.hash(path);
	}
}

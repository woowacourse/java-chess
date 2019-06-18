package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Path {
	private final List<Position> path;

	public Path(final List<Position> path) {
		this.path = new ArrayList<>(path);
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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(Position position : path) {
			stringBuilder.append(position);
		}
		return stringBuilder.toString();
	}
}

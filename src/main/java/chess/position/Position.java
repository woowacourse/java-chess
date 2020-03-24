package chess.position;

import java.util.HashMap;
import java.util.Map;

public class Position {

	private static final Map<String, Position> CACHE = new HashMap<>();

	static {
		for (File file : File.values()) {
			for (Rank rank : Rank.values()) {
				CACHE.put(getKey(file, rank), new Position(file, rank));
			}
		}
	}

	private final File file;
	private final Rank rank;

	private static String getKey(File file, Rank rank) {
		return file.getName() + rank.getName();
	}

	private Position(File file, Rank rank) {
		this.file = file;
		this.rank = rank;
	}

	public static Position of(File file, Rank rank) {
		return CACHE.get(getKey(file, rank));
	}
}

package chess.domain.position;

import java.util.List;
import java.util.stream.Collectors;

public class MoveInfo {
	private static final String DELIMITER = " ";
	private static final int FROM = 0;
	private static final int TO = 1;

	private final Position from;
	private final Position to;

	private MoveInfo(Position from, Position to) {
		this.from = from;
		this.to = to;
	}

	public static MoveInfo of(String moveInfo) {
		List<Position> infos = List.of(moveInfo.split(DELIMITER))
			.stream()
			.filter(info -> !info.equals("move"))
			.map(Position::of)
			.collect(Collectors.toList());
		return new MoveInfo(infos.get(FROM), infos.get(TO));
	}

	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}
}

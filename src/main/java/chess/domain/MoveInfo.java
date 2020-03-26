package chess.domain;

import java.util.Arrays;
import java.util.List;

public class MoveInfo {
	private static final String DELIMITER = " ";
	private static final int FROM = 1;
	private static final int TO = 2;
	private static final int MOVE = 0;

	private final String from;
	private final String to;

	private MoveInfo(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public static MoveInfo of(String moveInfo) {
		List<String> infos = Arrays.asList(moveInfo.split(DELIMITER));
		if (!"move".equals(infos.get(MOVE))) {
			throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
		}
		return new MoveInfo(infos.get(FROM), infos.get(TO));
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}
}

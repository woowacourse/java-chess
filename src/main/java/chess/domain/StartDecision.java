package chess.domain;

import java.util.Arrays;

public enum StartDecision {
	START("start"),
	END("end");

	private static final String INVALID_COMMAND = "잘못된 명령입니다.";
	private String decision;

	StartDecision(String decision) {
		this.decision = decision;
	}

	public static StartDecision of(String userDecision) {
		return Arrays.stream(StartDecision.values())
			.filter(d -> d.decision.equals(userDecision.toLowerCase()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_COMMAND));
	}
}

package chess.domain;

import java.util.Arrays;

public enum StartDecision {
	START("start"),
	END("end");

	private String decision;

	StartDecision(String decision) {
		this.decision = decision;
	}

	public static StartDecision of(String userDecision) {
		return Arrays.stream(StartDecision.values())
			.filter(d -> d.decision.equals(userDecision.toLowerCase()))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("start 또는 end로 답해주십시오"));
	}
}

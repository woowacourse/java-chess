package chess.domain.state;

import java.util.Arrays;

public enum StateType {
	READY("ready"),
	STARTED("started"),
	KING_CATCHED_FINISHED("king_catch_finished"),
	SUSPEND_FINISHED("suspend_finished"),
	;

	private final String state;

	StateType(String state) {
		this.state = state;
	}

	public static StateType of(String state) {
		return Arrays.stream(values())
			.filter(val -> val.state.equals(state))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않는 게임 상태입니다."));
	}

	public String getState() {
		return state;
	}
}

package chess.domain;

import java.util.Arrays;

public enum FinishFlag {
	FINISH(true, "Y"),
	ON_GOING(false, "N");

	private boolean flag;
	private String symbol;

	FinishFlag(boolean flag, String symbol) {
		this.flag = flag;
		this.symbol = symbol;
	}

	public static FinishFlag of(boolean isFinish) {
		return Arrays.stream(values())
				.filter(finishFlag -> finishFlag.flag == isFinish)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("일치하는 FinishFlag가 없습니다."));
	}

	public boolean isFinish(String symbol) {
		return FINISH.symbol.equals(symbol);
	}

	public String getSymbol() {
		return symbol;
	}
}

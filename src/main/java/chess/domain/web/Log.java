package chess.domain.web;

public class Log {
	private final String start;
	private final String end;

	public Log(String start, String end) {
		this.start = start;
		this.end = end;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}

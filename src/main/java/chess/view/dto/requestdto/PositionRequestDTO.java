package chess.view.dto.requestdto;

public class PositionRequestDTO {
	private final String from;
	private final String to;

	public PositionRequestDTO(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	@Override
	public String toString() {
		return "PositionRequestDTO{" +
			"from='" + from + '\'' +
			", to='" + to + '\'' +
			'}';
	}
}

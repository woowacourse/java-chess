package chess.view.dto.requestdto;

public class PositionRequestDTO {
	String from;
	String to;

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

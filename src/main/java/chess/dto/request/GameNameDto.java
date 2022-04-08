package chess.dto.request;

public class GameNameDto {

	private final String name;

	public GameNameDto(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

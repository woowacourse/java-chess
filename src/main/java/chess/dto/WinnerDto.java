package chess.dto;

public class WinnerDto {

	private final String winner;

	private WinnerDto(final String winner) {
		this.winner = winner;
	}

	public static WinnerDto of(final String winner) {
		return new WinnerDto(winner);
	}
}
